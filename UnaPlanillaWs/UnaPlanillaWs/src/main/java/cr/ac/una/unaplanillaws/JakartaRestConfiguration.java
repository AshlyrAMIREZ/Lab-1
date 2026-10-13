package cr.ac.una.unaplanillaws;

import cr.ac.una.unaplanillaws.util.ConstraintViolationExceptionMapper;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.UIManager;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configures Jakarta RESTful Web Services for the application.
 *
 * @author Juneau
 */
@ApplicationPath("ws") // TODO
public class JakartaRestConfiguration extends ResourceConfig {

    public JakartaRestConfiguration() {
        super();
        packages("cr.ac.una.unaplanillaws.controller", "io.swagger.v3.jaxrs2.integration.resources");
        registerCustomProvider();

   }
    private void registerCustomProvider(){
      Set<String> providersClassNames = new HashSet<>(){
          {
              add(ConstraintViolationExceptionMapper.class.getName());
          }
      };
            Map<String,Object> properties = new HashMap<>(){
          {
              put("jersey.config.server.provider.classnames", providersClassNames.stream().collect(Collectors.joining(",")));
              
          }
            }; 
            addProperties(Collections.unmodifiableMap(properties));
    }
}
