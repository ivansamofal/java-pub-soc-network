package hello.services.contractor.dineka;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hello.dtos.contractor.dineka.AbstractResponseDto;
import hello.dtos.contractor.dineka.DinekaCompanyDto;
import hello.dtos.contractor.dineka.DinekaDataDto;
import hello.dtos.contractor.dineka.DinekaResponseDto;
import hello.entities.ContractorCheck;
import hello.entities.Partner;
import hello.enums.PartnersEnum;
import hello.enums.StatusEnum;
import hello.factories.ContractorCheckDinekaFactory;
import hello.factories.DinekaResponseDtoFactory;
import hello.repositories.ContractorCheckRepository;
import hello.repositories.PartnerRepository;
import hello.services.RestService;
import hello.services.contractor.ContractorServiceInterface;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DinekaService implements ContractorServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(DinekaService.class);
    private static String API_URL = "http://dineka.ru/api/?token=";
    private static String API_TOKEN = "BD510B31-08FA-4F7F-ABD5-256D387CB94C";

    private ContractorCheckRepository contractorCheckRepository;
    private PartnerRepository partnerRepository;
    private RestService restService;

    public DinekaService(ContractorCheckRepository contractorCheckRepository, PartnerRepository partnerRepository, RestService restService) {
        this.contractorCheckRepository = contractorCheckRepository;
        this.partnerRepository = partnerRepository;
        this.restService = restService;
    }

    @Override
    public AbstractResponseDto getInfo(String regNumber) throws Exception {
        try {
            List<ContractorCheck> checkList = this.contractorCheckRepository.findAllByRegNumber(regNumber);

            if (checkList.isEmpty()) {
                HttpEntity entity = this.restService.get(API_URL.concat(API_TOKEN).concat("&reg_number=".concat(regNumber)));

                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    Partner partner = partnerRepository.findByAlias(PartnersEnum.DINEKA);

                    DinekaResponseDto dto = (new Gson()).fromJson(result, DinekaResponseDto.class);
                    this.saveChecks(dto.getData().getCompanies(), partner);

                    return dto;
                }

                throw new Exception("Entity not found!");
            } else {
                return DinekaResponseDtoFactory.create(checkList);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new Exception("Error: ".concat(e.getMessage()));
        }
    }

    public void saveChecks(List<DinekaCompanyDto> companies, Partner partner) {
        List<ContractorCheck> listChecks = new ArrayList<>();
        companies.forEach(i -> {
            listChecks.add(ContractorCheckDinekaFactory.create(i, partner));
        });

        if (!listChecks.isEmpty()) {
            contractorCheckRepository.saveAll(listChecks);
        }
    }
}
