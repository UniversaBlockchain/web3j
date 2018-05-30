package org.web3j.protocol.geth;

import org.junit.Test;
import org.web3j.protocol.ResponseTester;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.geth.response.PersonalEcRecover;
import org.web3j.protocol.geth.response.PersonalImportRawKey;
import org.web3j.protocol.geth.response.TxpoolContent;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResponseTest extends ResponseTester {

    @Test
    public void testPersonalEcRecover() {
        buildResponse("{\n"
                + "    \"jsonrpc\": \"2.0\",\n"
                + "    \"id\": 1,\n"
                + "    \"result\": \"0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54\"\n"
                + "}");

        PersonalEcRecover personalEcRecover = deserialiseResponse(
                PersonalEcRecover.class);
        assertThat(personalEcRecover.getRecoverAccountId(),
                is("0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54"));
    }

    @Test
    public void testPersonalImportRawKey() {
        buildResponse("{\n"
                + "    \"jsonrpc\": \"2.0\",\n"
                + "    \"id\": 1,\n"
                + "    \"result\": \"0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54\"\n"
                + "}");

        PersonalImportRawKey personalImportRawKey = deserialiseResponse(
                PersonalImportRawKey.class);
        assertThat(personalImportRawKey.getAccountId(),
                is("0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54"));
    }

    @Test
    public void TxpoolContent() {
        final String rawData = "{\n"
                + "    \"jsonrpc\": \"2.0\",\n"
                + "    \"id\": 1,\n"
                + "    \"result\": {\n"
                + "        \"pending\": {\n"
                + "            \"0x000064Fd35FC87026cA7FF868da2a7197bA66ABb\": {\n"
                + "                  \"4\": {\n"
                + "                      \"blockHash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
                + "                      \"blockNumber\":null,\n"
                + "                      \"from\": \"0x000064fd35fc87026ca7ff868da2a7197ba66abb\",\n"
                + "                      \"gas\": \"0x15f90\",\n"
                + "                      \"gasPrice\": \"0xee6b2800\",\n"
                + "                      \"hash\": \"0xaaae605918db5437fc0517a12b74f8af7c22569f54078fb025c1e89a8b694bb2\",\n"
                + "                      \"input\": \"0x\",\n"
                + "                      \"nonce\": \"0x4\",\n"
                + "                      \"to\": \"0x041fe8df8b4aaa868941eb877952f17babe57da5\",\n"
                + "                      \"transactionIndex\": \"0x0\",\n"
                + "                      \"value\": \"0x0\",\n"
                + "                      \"v\": \"0x1b\",\n"
                + "                      \"r\": \"0xb6fe0b6c462d2b353549af4de6b8c1b3d4010e949462267fbd0db560709897d5\",\n"
                + "                      \"s\": \"0x1d3357e426ba8fd4aff28c9578cfb868ab4fbed45f1ed42e6bbbf1084ed37fd9\""
                + "                  }"
                + "              },\n"
                + "              \"0x0001bCfC19CefAa1a9f87f0fC840e7Fd5B4B3c4D\":{"
                + "                  \"1\":{"
                + "                      \"blockHash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
                + "                      \"blockNumber\":null,\n"
                + "                      \"from\": \"0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d\",\n"
                + "                      \"gas\": \"0xf4240\",\n"
                + "                      \"gasPrice\": \"0xee6b2800\",\n"
                + "                      \"hash\": \"0xc7c12e3d995c295ed48de7ee81c865756691a4f291338e2079b93cbbcbda94ff\",\n"
                + "                      \"input\": \"0xf2c298be00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000035454f5335326a6b627971794b714276534d6f326d5673463274524e56737a3158476a64654643394553366a613277346d7543505a440000000000000000000000\",\n"
                + "                      \"nonce\": \"0x1\",\n"
                + "                      \"to\": \"0xd0a6e6c54dbc68db5db3a091b171a77407ff7ccf\",\n"
                + "                      \"transactionIndex\": \"0x0\",\n"
                + "                      \"value\": \"0x0\",\n"
                + "                      \"v\": \"0x26\",\n"
                + "                      \"r\": \"0x63f4adad225191442f510e05ba9105b891421d379d1484bc9bd7949b674af49a\",\n"
                + "                      \"s\": \"0x5e4040727dc3526af61dbf6c54173ad39ef35766fa021070ac71740d90aaedf\""
                + "                  },\n"
                + "                  \"2\": {"
                + "                      \"blockHash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
                + "                      \"blockNumber\":null,\"from\": \"0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d\",\n"
                + "                      \"gas\": \"0x5208\",\n"
                + "                      \"gasPrice\": \"0x51f4d5c00\",\n"
                + "                      \"hash\": \"0x0eb3cb8c4df3a7536f6b3568a7cf84cd6d7c27ead729e5cca5010d90d7e9e88d\",\n"
                + "                      \"input\": \"0x\",\n"
                + "                      \"nonce\": \"0x2\",\n"
                + "                      \"to\": \"0xd954528247cf9e64bd211f684bd9ff1785426b45\",\n"
                + "                      \"transactionIndex\": \"0x0\",\n"
                + "                      \"value\": \"0x8e1bc9bf040000\",\n"
                + "                      \"v\": \"0x25\",\n"
                + "                      \"r\": \"0x49aede07d1aca0910a7c69861ac3f6b6d0c59bcf637129944bda255ea6f14262\",\n"
                + "                      \"s\": \"0x735e9c0c9fc1d2a54e0b3fda062318e0a15a936ba2c8f24b2ef3704539b6f78a\"},\n"
                + "                  \"3\": {"
                + "                      \"blockHash\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
                + "                      \"blockNumber\":null,\n"
                + "                      \"from\": \"0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d\",\n"
                + "                      \"gas\": \"0x5208\",\n"
                + "                      \"gasPrice\": \"0x1a13b8600\",\n"
                + "                      \"hash\": \"0x0a196401574dcb7293d62089986f92ac1cf25193837e15ad3b53ac82c8ac54cd\",\n"
                + "                      \"input\": \"0x\",\n"
                + "                      \"nonce\": \"0x3\",\n"
                + "                      \"to\": \"0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d\",\n"
                + "                      \"transactionIndex\": \"0x0\",\n"
                + "                      \"value\": \"0x0\",\n"
                + "                      \"v\": \"0x25\",\n"
                + "                      \"r\": \"0xeacb949f5a0d09aeecc31b99a22071fdce9d7e02eff3f312ba6517b47588ee87\",\n"
                + "                      \"s\": \"0x6bee14dd1f84a277b59b3795a3ee8badb04c50a638748058110718bb6c5212dd\"\n"
                + "                  }\n"
                + "            }\n"
                + "        }\n"
                + "    }\n"
                + "}";

        buildResponse(rawData);

        final TxpoolContent txpoolContent = deserialiseResponse(TxpoolContent.class);
        final TxpoolContent.Result result = txpoolContent.getTxpoolContent();

        {
            final TxpoolContent.Result referenceGood = new TxpoolContent.Result(new HashMap<String, Map<BigInteger, Transaction>>() {{
                put("0x000064fd35fc87026ca7ff868da2a7197ba66abb", new HashMap<BigInteger, Transaction>() {{
                    put(BigInteger.valueOf(4), new Transaction(
                            "0xaaae605918db5437fc0517a12b74f8af7c22569f54078fb025c1e89a8b694bb2",
                            "0x4",
                            "0x0000000000000000000000000000000000000000000000000000000000000000",
                            null,
                            "0x0",
                            "0x000064fd35fc87026ca7ff868da2a7197ba66abb",
                            "0x041fe8df8b4aaa868941eb877952f17babe57da5",
                            "0x0",
                            "0x15f90",
                            "0xee6b2800",
                            "0x",
                            null,
                            null,
                            null,
                            "0xb6fe0b6c462d2b353549af4de6b8c1b3d4010e949462267fbd0db560709897d5",
                            "0x1d3357e426ba8fd4aff28c9578cfb868ab4fbed45f1ed42e6bbbf1084ed37fd9",
                            0x1b
                    ));
                }});

                put("0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d", new HashMap<BigInteger, Transaction>() {{
                    put(BigInteger.valueOf(1), new Transaction(
                            "0xc7c12e3d995c295ed48de7ee81c865756691a4f291338e2079b93cbbcbda94ff",
                            "0x1",
                            "0x0000000000000000000000000000000000000000000000000000000000000000",
                            null,
                            "0x0",
                            "0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d",
                            "0xd0a6e6c54dbc68db5db3a091b171a77407ff7ccf",
                            "0x0",
                            "0xf4240",
                            "0xee6b2800",
                            "0xf2c298be00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000035454f5335326a6b627971794b714276534d6f326d5673463274524e56737a3158476a64654643394553366a613277346d7543505a440000000000000000000000",
                            null,
                            null,
                            null,
                            "0x63f4adad225191442f510e05ba9105b891421d379d1484bc9bd7949b674af49a",
                            "0x5e4040727dc3526af61dbf6c54173ad39ef35766fa021070ac71740d90aaedf",
                            0x26
                    ));
                    put(BigInteger.valueOf(2), new Transaction(
                            "0x0eb3cb8c4df3a7536f6b3568a7cf84cd6d7c27ead729e5cca5010d90d7e9e88d",
                            "0x2",
                            "0x0000000000000000000000000000000000000000000000000000000000000000",
                            null,
                            "0x0",
                            "0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d",
                            "0xd954528247cf9e64bd211f684bd9ff1785426b45",
                            "0x8e1bc9bf040000",
                            "0x5208",
                            "0x51f4d5c00",
                            "0x",
                            null,
                            null,
                            null,
                            "0x49aede07d1aca0910a7c69861ac3f6b6d0c59bcf637129944bda255ea6f14262",
                            "0x735e9c0c9fc1d2a54e0b3fda062318e0a15a936ba2c8f24b2ef3704539b6f78a",
                            0x25
                    ));
                    put(BigInteger.valueOf(3), new Transaction(
                            "0x0a196401574dcb7293d62089986f92ac1cf25193837e15ad3b53ac82c8ac54cd",
                            "0x3",
                            "0x0000000000000000000000000000000000000000000000000000000000000000",
                            null,
                            "0x0",
                            "0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d",
                            "0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d",
                            "0x0",
                            "0x5208",
                            "0x1a13b8600",
                            "0x",
                            null,
                            null,
                            null,
                            "0xeacb949f5a0d09aeecc31b99a22071fdce9d7e02eff3f312ba6517b47588ee87",
                            "0x6bee14dd1f84a277b59b3795a3ee8badb04c50a638748058110718bb6c5212dd",
                            0x25
                    ));
                }});
            }});
            final Map<String, Map<BigInteger, Transaction>>
                    referencePending = referenceGood.getPending(),
                    resultPending = result.getPending();
            assertEquals(referencePending.keySet(), resultPending.keySet());
            for (final Map.Entry<String, Map<BigInteger, Transaction>> entryPerAddress : resultPending.entrySet()) {
                assertTrue(referencePending.containsKey(entryPerAddress.getKey()));
                final Map<BigInteger, Transaction>
                        referencePerNonceMap = referencePending.get(entryPerAddress.getKey()),
                        resultPerNonceMap = entryPerAddress.getValue();
                assertEquals(referencePerNonceMap.keySet(), resultPerNonceMap.keySet());

                for (final Map.Entry<BigInteger, Transaction> entryPerNonce : resultPerNonceMap.entrySet()) {
                    assertTrue(referencePerNonceMap.containsKey(entryPerNonce.getKey()));
                    final Transaction
                            referenceTransaction = referencePerNonceMap.get(entryPerNonce.getKey()),
                            resultTransaction = entryPerNonce.getValue();
                    assertEquals(referenceTransaction, resultTransaction);
                }
            }

            assertEquals(referenceGood, result);
        }

        {
            final TxpoolContent.Result referenceBad = new TxpoolContent.Result(new HashMap<String, Map<BigInteger, Transaction>>() {{
                put("0x000064fd35fc87026ca7ff868da2a7197ba66abb", new HashMap<BigInteger, Transaction>() {{
                    put(BigInteger.valueOf(4), new Transaction());
                }});
            }});
            assertNotEquals(referenceBad, result);
        }

        {
            final TxpoolContent.Result referenceBad = new TxpoolContent.Result(new HashMap<String, Map<BigInteger, Transaction>>() {{
                put("0x000064fd35fc87026ca7ff868da2a7197ba66abb", new HashMap<BigInteger, Transaction>() {{
                    put(BigInteger.valueOf(4), new Transaction());
                }});
                put("0x0001bcfc19cefaa1a9f87f0fc840e7fd5b4b3c4d", new HashMap<BigInteger, Transaction>() {{
                    put(BigInteger.valueOf(1), new Transaction());
                    put(BigInteger.valueOf(2), new Transaction());
                    put(BigInteger.valueOf(3), new Transaction());
                }});
            }});
            assertNotEquals(referenceBad, result);
        }
    }
}
