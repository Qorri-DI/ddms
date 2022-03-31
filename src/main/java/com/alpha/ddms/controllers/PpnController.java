package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.PpnModel;
import com.alpha.ddms.dto.PpnDto;
import com.alpha.ddms.dto.ResponseDto;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.PpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("ddms/v1/cmd/master/ppn")
public class PpnController {

    @Autowired
    PpnService ppnService;

    @Autowired
    DealerService dealerService;

    @PostMapping("save")
    public ResponseEntity<Object>postppn(
            @RequestBody final Map<String,Object>request
    ) throws Exception{


        String id = request.get("id").toString();
        String dealerId = request.get("dealerId").toString();
        String ppnDescription = request.get("ppnDescription").toString();
        float ppnRate = Float.parseFloat(request.get("ppnRate").toString());
        float ppnRatePrevious = Float.parseFloat(request.get("ppnRatePrevious").toString());
        String effectiveStartDate = request.get("effectiveStartDate").toString();
        String effectiveEndDate = request.get("effectiveEndDate").toString();
        String status = request.get("status").toString();

        Optional<PpnModel> cekid = ppnService.findPpnId(id);

        if (id.isEmpty()){
            String response = "Masukkan id";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{

            if (cekid.isPresent() || cekid == null){
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date esd = dateFormat.parse(effectiveStartDate);
                Date eed = dateFormat.parse(effectiveEndDate);

//                LocalDateTime stdate = LocalDateTime.parse(effectiveStartDate,formatter);
//                LocalDateTime eddate = LocalDateTime.parse(effectiveEndDate,formatter);

                Optional<PpnModel>cekppnid = ppnService.findPpnId(id);

                PpnModel ppnModel = new PpnModel();
                DealerModel dealerModel = dealerService.findById(dealerId).get();

                ppnModel.setPpn_id(id);
                ppnModel.setDealerModel(dealerModel);
                ppnModel.setDescription(ppnDescription);
                ppnModel.setPpn_rate(ppnRate);
                ppnModel.setPpn_rate_previous(ppnRatePrevious);

                ppnModel.setEffective_start_date(esd);
                ppnModel.setEffective_end_date(eed);
                ppnModel.setPpn_status(status);

                PpnModel saveppn = ppnService.savePpn(ppnModel);

                Map<String,Object>ret = new LinkedHashMap<>();
                ret.put("id",id);
                ret.put("dealerId", ppnModel.getDealerModel());
                ret.put("description",ppnDescription);
                ret.put("ppn rate", ppnRate);
                ret.put("ppn rate previous", ppnRatePrevious);
                ret.put("effective start date",esd);
                ret.put("effective end date", eed);
                ret.put("ppn status", status);

                PpnDto ppnDto = new PpnDto();
                ppnDto.setId(ppnModel.getPpn_id());
                ppnDto.setDealerId(ppnModel.getDealerModel().getDealer_code());
                ppnDto.setPpnDescription(ppnModel.getDescription());
                ppnDto.setPpnRate(ppnModel.getPpn_rate());
                ppnDto.setPpnRatePrevious(ppnModel.getPpn_rate_previous());
                ppnDto.setEffectiveStartDate(ppnModel.getEffective_start_date());
                ppnDto.setEffectiveEndDate(ppnModel.getEffective_end_date());
                ppnDto.setStatus(ppnModel.getPpn_status());

                ResponseDto responseDTO = new ResponseDto();
                responseDTO.setCode(201);
                responseDTO.setData(ppnDto);
                responseDTO.setMessage("process Successed");
                responseDTO.setStatus("S");

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            else {

                PpnModel ppnModel = ppnService.findPpnId(id).get();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date esd = dateFormat.parse(effectiveStartDate);
                Date eed = dateFormat.parse(effectiveEndDate);

                ppnModel.setDealerModel(dealerService.findById(dealerId).get());
                ppnModel.setDescription(ppnDescription);
                ppnModel.setPpn_rate(ppnRate);
                ppnModel.setPpn_rate_previous(ppnRatePrevious);
                ppnModel.setEffective_start_date(esd);
                ppnModel.setEffective_end_date(eed);
                ppnModel.setPpn_status(status);

                ppnService.updatePpn(ppnModel);

                ResponseDto responseDTO = new ResponseDto();
                responseDTO.setCode(201);
                responseDTO.setData(ppnModel);
                responseDTO.setMessage("process Successed");
                responseDTO.setStatus("S");

                return new ResponseEntity<>(responseDTO,HttpStatus.OK);

            }

        }

    }

    @GetMapping("get/{id}")
    public ResponseEntity<?>getppn(
            @PathVariable("id")String id
    ){
        Optional<PpnModel> cekid = ppnService.findPpnId(id);

        if (cekid.isPresent()){
            PpnModel ppnModel = ppnService.findPpnId(id).get();

            return new ResponseEntity<>(ppnModel,HttpStatus.OK);
        }
        else {

            String response = "No Data Found";
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }


    }

}
