package org.web3j.protocol.geth.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * txpool_content (Ethereum Classic network).
 * <p>
 * Note that only <code>pending</code> field is parsed; <code>queued</code> is ignored for now.
 */
public class TxpoolContentETC extends Response<TxpoolContentETC.Result> {

    public static class Result {

        private Map<String, Map<BigInteger, List<Transaction>>> pending;

        public Result() {
        }

        public Result(Map<String, Map<BigInteger, List<Transaction>>> pending) {
            this.pending = Collections.unmodifiableMap(
                    pending.entrySet()
                            .stream()
                            .collect(Collectors.toMap(
                                    (Map.Entry<String, Map<BigInteger, List<Transaction>>> e) ->
                                            e.getKey().toLowerCase(),
                                    (Map.Entry<String, Map<BigInteger, List<Transaction>>> e) ->
                                            Collections.unmodifiableMap(e.getValue().entrySet()
                                                    .stream()
                                                    .collect(Collectors.toMap(
                                                            (Map.Entry<BigInteger, List<Transaction>> e1) ->
                                                                    e1.getKey(),
                                                            (Map.Entry<BigInteger, List<Transaction>> e1) ->
                                                                    Collections.unmodifiableList(e1.getValue())
                                                    )))
                            ))
            );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (!(o instanceof Result)) {
                return false;
            } else {
                Result that = (Result) o;
                final Map<String, Map<BigInteger, List<Transaction>>>
                        thisPending = this.getPending(),
                        thatPending = that.getPending();
                return (true &&
                        // The set of "from"s for this-side matches on on "that"-side.
                        thisPending.keySet().equals(thatPending.keySet()) &&
                        // For every "from" on this-side and on "that"-side,
                        // the nonces and transactions match.
                        thisPending.entrySet().stream().allMatch(e -> {
                            final String thisFrom = e.getKey();
                            final Map<BigInteger, List<Transaction>>
                                    thisMapForFrom = e.getValue(),
                                    thatMapForFrom = thatPending.get(thisFrom);
                            return (true &&
                                    thatPending.containsKey(thisFrom) &&
                                    // On "this" and on "that" side, we have matching sets of nonces.
                                    thisMapForFrom.keySet().equals(thatMapForFrom.keySet()) &&
                                    // On "this" and on "that" side, for the specified "from" value,
                                    // we have matching nonce-to-transaction-list maps.
                                    thisMapForFrom.entrySet()
                                            .stream()
                                            .allMatch((Map.Entry<BigInteger, List<Transaction>> e2) -> {
                                                        final List<Transaction>
                                                                thisList = e2.getValue(),
                                                                thatList = thatMapForFrom.get(e2.getKey());
                                                        return (true &&
                                                                thisList.size() == thatList.size() &&
                                                                IntStream.range(0, thisList.size()).allMatch(
                                                                        i -> thisList.get(i).equals(thatList.get(i))
                                                                ));
                                                    }
                                            )
                            );
                        })
                );
            }
        }

        public String toString() {
            final int size = pending.size();
            final int hashCode = hashCode();
            return String.format("TxpoolContent.Result(<pending %s hashes: #%08X>):%s",
                    size, hashCode, pending.entrySet());
        }

        /**
         * Get the Map, which is grouped by (has keys) the from-address of pending transactions;
         * and, for each from-address, the value is another map with all the transactions
         * outgoing from that address.
         * <p>
         * The second map is grouped by (has keys) the nonce fields of transactions;
         * and the value for each key is the list of transactions themselves.
         */
        public Map<String, Map<BigInteger, List<Transaction>>> getPending() {
            return pending;
        }
    }

    public TxpoolContentETC.Result getTxpoolContentETC() {
        final Result rawResult = getResult();
        assert rawResult != null : "Could not parse JSON to TxpoolContentETC class";
        return new Result(rawResult.pending);
    }
}
