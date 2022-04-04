package com.alpha.ddms.controllers;

import com.alpha.ddms.domains.*;
import com.alpha.ddms.dto.*;
import com.alpha.ddms.services.DealerService;
import com.alpha.ddms.services.PpnService;
import com.alpha.ddms.utils.Validasi;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/ppn")
public class PpnController {

    @Autowired
    PpnService ppnService;

    @Autowired
    DealerService dealerService;


    List number;
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<List> handleNumberFormatException(NumberFormatException e){
        String name[] = e.getLocalizedMessage().split(":",2);
        number = new ArrayList<>();
        String tampung = name[1].replace("\"","");
        number.add("Invalid number : " + tampung);
        return new ResponseEntity<>(number,HttpStatus.BAD_REQUEST);
    }

    List parse;
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<List> handleParseFormatException(ParseException e){
        String name[] = e.getLocalizedMessage().split(":",2);
        parse = new ArrayList<>();
        String tampung = name[1].replace("\"","");
        parse.add("Invalid date : " + tampung);
        return new ResponseEntity<>(parse,HttpStatus.BAD_REQUEST);
    }



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

            if (!cekid.isPresent() || cekid == null){
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
                ppnDto.setAhmDealerCode("00999");
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
//                Date esd = dateFormat.parse(effectiveStartDate);
//                Date eed = dateFormat.parse(effectiveEndDate);

                if (!dealerId.equalsIgnoreCase("")){
                    ppnModel.setDealerModel(dealerService.findById(dealerId).get());
                }

                if (!ppnDescription.equalsIgnoreCase("")){
                    ppnModel.setDescription(ppnDescription);

                }
                else {
                    ppnModel.setDescription(ppnModel.getDescription());
                }

                if (ppnRate != 0 && ppnRate > 0) {

                    ppnModel.setPpn_rate(ppnRate);
                }
                else if (ppnRate != 0 && ppnRate < 0){
                    String response = "ppn rate tidak boleh negatif";
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }else {
                    ppnModel.setPpn_rate(ppnModel.getPpn_rate());
                }

                if (ppnRatePrevious !=0 && ppnRatePrevious > 0){
                    ppnModel.setPpn_rate_previous(ppnRatePrevious);
                }
                else if (ppnRatePrevious !=0 && ppnRatePrevious < 0){
                    String response = "ppn rate prevoius tidak boleh negatif";
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }else {
                    ppnModel.setPpn_rate_previous(ppnModel.getPpn_rate_previous());
                }

                if (!effectiveStartDate.equalsIgnoreCase("")){
                    Date esd = dateFormat.parse(effectiveStartDate);
                    ppnModel.setEffective_start_date(esd);
                }
                else{
                    if(effectiveStartDate.equalsIgnoreCase("")){
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        ppnModel.setEffective_start_date(ppnModel.getEffective_start_date());
                    }else{
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        dateFormat1.setLenient(false);
                        Date esd = dateFormat.parse(effectiveStartDate);
                        ppnModel.setEffective_start_date(esd);
                    }
                }


                if (!effectiveEndDate.equalsIgnoreCase("")){
                    Date eed = dateFormat.parse(effectiveEndDate);
                    ppnModel.setEffective_end_date(eed);
                }
                else {
                    if(effectiveEndDate.equalsIgnoreCase("")){
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        ppnModel.setEffective_end_date(ppnModel.getEffective_end_date());

                    }else {
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        dateFormat1.setLenient(false);
                        Date eed = dateFormat.parse(effectiveEndDate);
                        ppnModel.setEffective_end_date(eed);
                    }
                }

                if (!status.equalsIgnoreCase("")){
                    ppnModel.setPpn_status(status);
                }
                else{
                    ppnModel.setPpn_status(ppnModel.getPpn_status());
                }

                ppnService.updatePpn(ppnModel);

                PpnUpdateDTO ppnUpdateDTO = new PpnUpdateDTO();
                ppnUpdateDTO.setId(ppnModel.getPpn_id());
                ppnUpdateDTO.setDealerId(ppnModel.getDealerModel().getDealer_code());
                ppnUpdateDTO.setPpnDescription(ppnModel.getDescription());
                ppnUpdateDTO.setPpnRate(ppnModel.getPpn_rate());
                ppnUpdateDTO.setPpnRatePrevious(ppnModel.getPpn_rate_previous());
                ppnUpdateDTO.setEffectiveStartDate(ppnModel.getEffective_start_date());
                ppnUpdateDTO.setEffectiveEndDate(ppnModel.getEffective_end_date());
                ppnUpdateDTO.setStatus(ppnModel.getPpn_status());

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

            PpnGetIddTO ppnGetIddTO = new PpnGetIddTO();
            ppnGetIddTO.setId(ppnModel.getPpn_id());
            ppnGetIddTO.setDealerId(ppnModel.getDealerModel().getDealer_code());
            ppnGetIddTO.setPpnDescription(ppnModel.getDescription());
            ppnGetIddTO.setPpnRate(ppnModel.getPpn_rate());
            ppnGetIddTO.setPpnRatePrevious(ppnModel.getPpn_rate_previous());
            ppnGetIddTO.setEffectiveStartDate(ppnModel.getEffective_start_date());
            ppnGetIddTO.setEffectiveEndDate(ppnModel.getEffective_end_date());
            ppnGetIddTO.setStatus(ppnModel.getPpn_status());

            ResponseDto responseDTO = new ResponseDto();
            responseDTO.setCode(201);
            responseDTO.setStatus("S");
            responseDTO.setData(ppnGetIddTO);
            responseDTO.setMessage("process Successed");

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
        else {

            String response = "No Data Found";
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }


    }

    @PostMapping("getActivePpn")
    public ResponseEntity<Object>getactive(
            @RequestBody final Map<String,Object>request
    )throws Exception{
        String dealerId = request.get("dealerId").toString();
        String queryDate = request.get("queryDate").toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date qd;
        if (queryDate.equalsIgnoreCase("") || queryDate == null){
            qd = dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()).toString());
        }else {
            qd = dateFormat.parse(queryDate);
        }

        Optional<PpnModel> cekactiveppn = ppnService.findactiveppn(dealerId,qd);

        if (!cekactiveppn.isPresent()){
            String response = "Mohon isi data dengan benar";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        else {

            Optional<PpnModel> ppnModels = ppnService.findactiveppn(dealerId,qd);

            GetActiveDTO getActiveDTO = new GetActiveDTO();
            getActiveDTO.setId(ppnModels.get().getPpn_id());
            getActiveDTO.setDealerId(ppnModels.get().getDealerModel().getDealer_code());
            getActiveDTO.setPpnDescription(ppnModels.get().getDescription());
            getActiveDTO.setPpnRate(ppnModels.get().getPpn_rate());
            getActiveDTO.setPpnRatePrevious(ppnModels.get().getPpn_rate_previous());
            getActiveDTO.setEffectiveStartDate(ppnModels.get().getEffective_start_date());
            getActiveDTO.setEffectiveEndDate(ppnModels.get().getEffective_end_date());
            getActiveDTO.setStatus(ppnModels.get().getPpn_status());

            ResponseDto responseDTO = new ResponseDto();
            responseDTO.setStatus("S");
            responseDTO.setCode(201);
            responseDTO.setMessage("Processed Successed");
            responseDTO.setData(getActiveDTO);

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }

    }

    @PostMapping("listByDealerId")
    public ResponseEntity<Object>getlist(
            @RequestBody final Map<String, Object>request
    ){
        String dealerId = request.get("dealerId").toString();
        String status = request.get("status").toString();
        Integer offset = Integer.parseInt(request.get("offset").toString());
        Integer limit = Integer.parseInt(request.get("limit").toString());

        List<PpnModel>cek = ppnService.findds(dealerId,status,offset,limit).toList();

        if (cek.isEmpty()){
            String response = "data ga ada";

            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        else {
            List<PpnModel> ppnModel = ppnService.findds(dealerId,status,offset,limit).toList();

            ResponseDto responseDTO = new ResponseDto();
            responseDTO.setStatus("S");
            responseDTO.setCode(201);
            responseDTO.setMessage("Proccessed Succesed");
            responseDTO.setData(ppnModel);

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

}
