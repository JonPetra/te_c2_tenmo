package com.techelevator;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcTransferDaoTests extends TenmoDaoTests {

    private static final Transfer TRANSFER_1 =
            new Transfer(3091, 2, 2, 2091, 2092, 5.00);
    private static final Transfer TRANSFER_2 =
            new Transfer(3092, 2, 2, 2092, 2091, 50.99);
    private static final Transfer TRANSFER_3 =
            new Transfer(3093, 2, 2, 2093, 2092, 500.06);
    private static final Transfer TRANSFER_4 =
            new Transfer(3094, 2, 2, 2094, 2091, 100.00);
    private static final Transfer TRANSFER_5 =
            new Transfer(3095, 2, 2, 2095, 2094, 95.00);
    private static final Transfer TRANSFER_6 =
            new Transfer(3096, 2, 2, 2096, 2093, 5000.00);

    private Transfer testTransfer;
    private JdbcTransferDao tSut;
    private JdbcAccountDao aSut;

    @Before
    public void setup() {
        tSut = new JdbcTransferDao(dataSource);
        aSut = new JdbcAccountDao(dataSource);
        testTransfer = new Transfer(null, 2, 2, 2094, 2091, 52.00);
    }

    //Transfer createTransfer(Transfer transfer);
    @Test
    public void createTransfer_returns_transfer_with_id_and_expected_values() {
        Transfer createdTransfer = tSut.createTransfer(testTransfer);

        Assert.assertNotNull(createdTransfer);

        Integer newId = createdTransfer.getTransferId();
        Assert.assertTrue(newId > 0);

        testTransfer.setTransferId(newId);
        assertTransfersMatch(testTransfer, createdTransfer);

        //Assert send added amount to 2091
        Double balance = aSut.getBalance(1094);
        Double expected = 948.00;
        Assert.assertEquals(expected, balance);

        //Assert withdraw subtracted amount from 2094
        balance = aSut.getBalance(1091);
        expected = 1052.00;
        Assert.assertEquals(expected, balance);
    }

    //List<Transfer> listTransfersByUser(Integer userId);
    @Test
    public void listTransfersByUser_returns_valid_list() {
        List<Transfer> transfers = tSut.listTransfersByUser(1091);
        Assert.assertEquals(3, transfers.size());
        assertTransfersMatch(TRANSFER_1, transfers.get(0));
        assertTransfersMatch(TRANSFER_2, transfers.get(1));
        assertTransfersMatch(TRANSFER_4, transfers.get(2));
    }

    //Transfer getTransferById(Integer transferId);
    @Test
    public void getTransferById_returns_correct_transfer() {
        Transfer transfer = tSut.getTransferById(3091);
        assertTransfersMatch(TRANSFER_1, transfer);

        Transfer transfer1 = tSut.getTransferById(3096);
        assertTransfersMatch(TRANSFER_6, transfer1);
    }

    //helper method
    private void assertTransfersMatch(Transfer expected, Transfer actual) {
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getTransferTypeId(), actual.getTransferTypeId());
        Assert.assertEquals(expected.getTransferStatusId(), actual.getTransferStatusId());
        Assert.assertEquals(expected.getAccountFrom(), actual.getAccountFrom());
        Assert.assertEquals(expected.getAccountTo(), actual.getAccountTo());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }
}
