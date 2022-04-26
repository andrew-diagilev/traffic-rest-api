package com.traffic.api.services;

import com.traffic.api.DAO.AmountPlateTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class AmountPlateTypeService {
    @Autowired
    AmountPlateTypeDAO amountPlateTypeDAO;

    public List<AmountPlateTypeDAO.AmountView> getAmountPlateType() {
        return amountPlateTypeDAO.getLast();
    }
}

  /*  Calendar start = Calendar.getInstance();
        start.add(Calendar.DATE, -1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                start.set(Calendar.HOUR_OF_DAY, 0);
                start.set(Calendar.MINUTE, 0);
                start.set(Calendar.SECOND, 0);
                dateFormat.format(start.getTime());*/