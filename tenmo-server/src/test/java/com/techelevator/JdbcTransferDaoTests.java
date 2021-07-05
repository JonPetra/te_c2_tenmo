package com.techelevator;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTransferDaoTests extends TenmoDaoTests {

    private static final Transfer TRANSFER_1 =
            new Transfer(3001, 2, 2, 2001, 2002, 5.00);
    private static final Transfer TRANSFER_2 =
            new Transfer(3002, 2, 2, 2002, 2001, 50.99);
    private static final Transfer TRANSFER_3 =
            new Transfer(3003, 2, 2, 2003, 2002, 500.06);
    private static final Transfer TRANSFER_4 =
            new Transfer(3004, 2, 2, 2004, 2001, 100.00);
    private static final Transfer TRANSFER_5 =
            new Transfer(3005, 2, 2, 2005, 2004, 95.00);
    private static final Transfer TRANSFER_6 =
            new Transfer(3006, 2, 2, 2006, 2003, 5000.00);

    private Transfer testTransfer;
    private JdbcTransferDao sut;

    @Before
    public void setup() {
        sut = new JdbcTransferDao(dataSource);
        testTransfer = new Transfer(null, 2, 2, 2004, 2001, 52.00);
    }

    //Transfer createTransfer(Transfer transfer);
    @Test
    public void createTransfer_returns_transfer_with_id_and_expected_values() {
        Transfer createdTransfer = sut.createTransfer(testTransfer);

        Assert.assertNotNull(createdTransfer);

        Integer newId = createdTransfer.getTransferId();
        Assert.assertTrue(newId > 0);

        testTransfer.setTransferId(newId);
        assertTransfersMatch(testTransfer, createdTransfer);

        //Assert send added amount to 2001

        //Assert withdraw subtracted amount from 2004
    }

    //List<Transfer> listTransfersByUser(Integer userId);
    @Test
    public void listTransfersByUser_returns_valid_list() {
        List<Transfer> transfers = sut.listTransfersByUser(2001);
        Assert.assertEquals(3, transfers.size());
        assertTransfersMatch(TRANSFER_1, transfers.get(0));
        assertTransfersMatch(TRANSFER_2, transfers.get(1));
        assertTransfersMatch(TRANSFER_4, transfers.get(2));
    }

    //Transfer getTransferById(Integer transferId);
    @Test
    public void getTransferById_returns_correct_transfer() {
        Transfer transfer = sut.getTransferById(3001);
        assertTransfersMatch(TRANSFER_1, transfer);

        transfer = sut.getTransferById(1006);
        assertTransfersMatch(TRANSFER_6, transfer);
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
