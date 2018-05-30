package org.web3j.protocol.geth.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.Transaction;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * txpool_content.
 */
public class TxpoolContent extends Response<TxpoolContent.Result> {

    public static class Result {

        private Map<String, Map<BigInteger, Transaction>> pending;

        public Result() {
        }

        public Result(Map<String, Map<BigInteger, Transaction>> pending) {
            this.pending = Collections.unmodifiableMap(
                    pending.entrySet()
                            .stream()
                            .collect(Collectors.toMap(
                                    e -> e.getKey().toLowerCase(),
                                    e -> Collections.unmodifiableMap(e.getValue())
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
                final Map<String, Map<BigInteger, Transaction>>
                        thisPending = this.getPending(),
                        thatPending = that.getPending();
                return (true &&
                        // The set of "from"s for this-side matches on on "that"-side.
                        thisPending.keySet().equals(thatPending.keySet()) &&
                        // For every "from" on this-side and on "that"-side,
                        // the nonces and transactions match.
                        thisPending.entrySet().stream().allMatch(e -> {
                            final String thisFrom = e.getKey();
                            final Map<BigInteger, Transaction>
                                    thisMapForFrom = e.getValue(),
                                    thatMapForFrom = thatPending.get(thisFrom);
                            return (true &&
                                    thatPending.containsKey(thisFrom) &&
                                    // On "this" and on "that" side, we have matching sets of nonces.
                                    thisMapForFrom.keySet().equals(thatMapForFrom.keySet()) &&
                                    // On "this" and on "that" side, for the specified "from" value,
                                    // we have matching nonce-to-transaction maps.
                                    thisMapForFrom.entrySet().stream().allMatch(e2 ->
                                            e2.getValue().equals(thatMapForFrom.get(e2.getKey()))
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
         * and the values are the transactions themselves.
         */
        public Map<String, Map<BigInteger, Transaction>> getPending() {
            return pending;
        }
    }

    public TxpoolContent.Result getTxpoolContent() {
        final Result rawResult = getResult();
        assert rawResult != null : "Could not parse JSON to TxpoolContent class";
        return new Result(rawResult.pending);
    }
}
