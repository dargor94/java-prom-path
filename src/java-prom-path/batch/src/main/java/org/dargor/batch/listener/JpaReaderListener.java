package org.dargor.batch.listener;

import org.dargor.batch.entity.Customer;
import org.springframework.batch.core.ItemReadListener;

public class JpaReaderListener implements ItemReadListener<Customer> {

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Customer item) {
        System.out.println("----------------------- Printing Customer " + item + " ----------------------- -----------------------");
    }


    @Override
    public void onReadError(Exception ex) {

    }

}
