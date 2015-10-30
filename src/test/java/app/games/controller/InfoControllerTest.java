package app.games.controller;

import app.games.configuration.ApplicationConfig;
import app.games.configuration.HibernateConfig;
import app.games.db.dao.GamerDAO;
import app.games.db.dao.GamerDAOImpl;
import app.games.db.model.Gamer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author abogaichuk
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, ApplicationConfig.class})
@WebAppConfiguration*/
public class InfoControllerTest {
    /*@Mock
    private GamerDAO gamerDAO;

    @Autowired
    public ViewResolver viewResolver;
    @Autowired
    private Validator blackJackValidator;

    @Autowired
    private Gamer gamer;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InfoController())
                .setValidator(blackJackValidator)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void info() throws Exception {
        when(gamerDAO.getById(1L)).thenReturn(gamer);
        when(gamerDAO.getTotalBalance(1L)).thenReturn((long) 1000);

        mockMvc.perform(get("games/info/{account}", 1L)).andReturn().getModelAndView().getModelMap();

        verify(gamerDAO, times(1)).getById(1L);
        verify(gamerDAO, times(1)).getTotalBalance(1L);
    }*/
}
