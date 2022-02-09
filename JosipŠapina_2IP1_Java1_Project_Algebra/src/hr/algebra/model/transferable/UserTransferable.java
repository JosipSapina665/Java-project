/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model.transferable;

import java.awt.datatransfer.Transferable;
import hr.algebra.model.User;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author josip
 */
public class UserTransferable implements Transferable {

    public static DataFlavor USER_FLAVOR = new DataFlavor(User.class, "Movie");
    public static DataFlavor[] SUPPORTED_FLAVORS = {USER_FLAVOR};

    public final User user;

    public UserTransferable(User user) {
        this.user = user;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(USER_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return user;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
