package com.alpha.ddms.dto;

import java.util.List;

public class DealerDTOlist {
    List<DealerDTO> listDealer ;
    private int dataOfRecord;

    public List<DealerDTO> getListDealer() {
        return listDealer;
    }

    public void setListDealer(List<DealerDTO> listDealer) {
        this.listDealer = listDealer;
    }

    public int getDataOfRecord() {
        return dataOfRecord;
    }

    public void setDataOfRecord(int dataOfRecord) {
        this.dataOfRecord = dataOfRecord;
    }
}