/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.service.tugas_01;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Nafi'
 */
@Controller
public class databaseController_xml {
    
    @RequestMapping(value="/viewXML", produces={MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<DataMahasiswa> getDatamhs(){
        
        List<DataMahasiswa> datamahasiswa = new ArrayList<>();
        
        DataMahasiswaJpaController controller = new DataMahasiswaJpaController();
        
        try {
            datamahasiswa = controller.findDataMahasiswaEntities();
        } catch (Exception e){}
        
        return datamahasiswa;
    }       
}
