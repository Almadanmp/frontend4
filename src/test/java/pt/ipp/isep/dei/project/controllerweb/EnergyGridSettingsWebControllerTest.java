package pt.ipp.isep.dei.project.controllerweb;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class EnergyGridSettingsWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EnergyGridSettingsWebController energyGridSettingsWebController;

    @BeforeEach
    void setData(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void seeIfCreateEnergyGridWorks() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        this.mockMvc.perform(post("/gridSettings/grids")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"B building\",\n" +
                        "  \"houseID\": \"7\",\n" +
                        "  \"maxContractedPower\": \"45\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    public void seeIfAttachRoomToGridPostWorks() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();

        ResultActions resultActions = this.mockMvc.perform(post("/gridSettings/grids/B building")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\t\"name\": \"B102\",\n" +
                        "\t\"description\": \"Reprographics Centre\",\n" +
                        "\t\"floor\": \"1\",\n" +
                        "\t\"width\": 7,\n" +
                        "\t\"length\": 21,\n" +
                        "\t\"height\": 3.5\n" +
                        "}"))
                .andExpect(status().isOk());
        int status = resultActions.andReturn().getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void seeIfGetAllGridsDoesNotWork() throws Exception {
        String uri = "/gridSettings/grids";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

//    @Test
//    public void seeIfCreateEnergyGridWorksMethod() {
//
//        this.mockMvc = MockMvcBuilders.standaloneSetup(energyGridSettingsWebController).build();
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("Energy grid created and added to the house with success!",HttpStatus.CREATED);
//
//
//        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
//        energyGridDTO.setName("B building");
//        energyGridDTO.setHouseID("7");
//        energyGridDTO.setMaxContractedPower(45);
//        energyGridDTO.setRoomDTOS(new ArrayList<>());
//        energyGridDTO.setPowerSourceDTOS(new ArrayList<>());
//        EnergyGrid energyGrid = EnergyGridMapper.dtoToObject(energyGridDTO);
//
//
//        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void mockTest(){
//        ResponseEntity<String> expectedResult = new ResponseEntity<>("Energy grid created and added to the house with success!",
//                HttpStatus.CREATED);
//
//        EnergyGridSettingsWebController energyGridSettingsWebController = new EnergyGridSettingsWebController();
//
//        EnergyGridDTO energyGridDTO = new EnergyGridDTO();
//        energyGridDTO.setName("EG1");
//        energyGridDTO.setMaxContractedPower(0);
//        energyGridDTO.setHouseID("01");
//        List<PowerSourceDTO> powerSourceDTOList = new ArrayList<>();
//        energyGridDTO.setPowerSourceDTOS(powerSourceDTOList);
//        List<RoomDTO> list = new ArrayList<>();
//        energyGridDTO.setRoomDTOS(list);
//        Mockito.when(mockRepo.save(EnergyGridMapper.dtoToObject(energyGridDTO))).thenReturn(EnergyGridMapper.dtoToObject(energyGridDTO));
//
//        ResponseEntity<String> actualResult = energyGridSettingsWebController.createEnergyGrid(energyGridDTO);
//
//        assertEquals(expectedResult, actualResult);
//    }
}
