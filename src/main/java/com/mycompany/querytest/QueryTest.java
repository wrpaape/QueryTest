import com.cyc.kb.ContextFactory;
import com.cyc.kb.KbCollection;
import com.cyc.kb.KbCollectionFactory;
import com.cyc.kb.KbIndividual;
import com.cyc.kb.exception.CreateException;
import com.cyc.kb.exception.KbTypeException;
import com.cyc.session.CycSessionManager;
import com.cyc.session.exception.SessionException;
import java.util.Set;
 
public class QueryTest {
    //Author: Reid Paape. 
    //Licence: CC0
    public static  void main(String[] argv){
 
        try {
            CycSessionManager.getCurrentSession().getOptions().setDefaultContext(
            ContextFactory.getDefaultContext(ContextFactory.UV_MT, ContextFactory.INFERENCE_PSC));
               
//            KbCollection planetInTheSolarSystem = KbCollectionFactory.get("PlanetInTheSolarSystem");
            KbCollection queryCollection = KbCollectionFactory.get(argv[0]);
            Set<KbIndividual> individuals = (Set<KbIndividual>) queryCollection.getInstances();
            for (KbIndividual individual : individuals) {
                System.out.println("Hello '"
                        + individual.toString() + "'.");
            }
        } catch (KbTypeException | CreateException | SessionException e) {
            System.out.println("A problem occured while fetching your query:" + e);
        }
    }
}