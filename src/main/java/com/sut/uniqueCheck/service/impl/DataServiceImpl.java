package com.sut.uniqueCheck.service.impl;

import com.sut.uniqueCheck.dto.Data;
import com.sut.uniqueCheck.repository.DataRepository;
import com.sut.uniqueCheck.service.DataService;
import com.sut.uniqueCheck.strategy.Shingle;
import org.springframework.beans.factory.annotation.Autowired;

public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    {
        addFieldToTable("Esli ya cheshu v zatylke Ne beda, V golove moej opilki Da, da, da.", 100);
        addFieldToTable("I hotya tam i opilki, No krichalki i draznilki,", 100);
        addFieldToTable("A tak-zhe sopelki, pyhtelki Sochinyayu ya neploho inogda.", 100);
        addFieldToTable("Da! Horosho zhivyot na svete Vinni Puh, Ot togo poyot on ehti pesni vsluh. I ne vazhno, chem on zanyat, Esli on hudet' ne stanet,", 100);
        addFieldToTable("Esli konechno vovremya podkrepit'sya. Da! Tram-pa-ram-pa-ram-pa-ram-pa. Tram-pam-pa.", 100);
        addFieldToTable("Trum-pu-rum-pu-trum-pu-rum-pu. Tram-pam-pam. Tram-pa-ram-pa-ram-pa-ram-pa.", 100);
    }


    @Override
    public void add(String text, int count) {

        final Double minUniq = dataRepository.findAll().stream()
                                             .map(Data::getText)
                                             .map(field -> checkUniq(text, count, field))
                                             .sorted()
                                             .findFirst()
                                             .get();


        addFieldToTable(text, minUniq);
    }

    private double checkUniq(String text, int count, String oldText) {
        return new Shingle().getUniq(text, count, oldText);
    }

    private void addFieldToTable(String text, double uniq) {
        Data data = new Data();
        data.setText(text);
        data.setUniq(uniq);
        dataRepository.save(data);
    }

}
