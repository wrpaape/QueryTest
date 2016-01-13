import com.cyc.kb.ContextFactory;
import com.cyc.kb.KbCollection;
import com.cyc.kb.KbCollectionFactory;
import com.cyc.kb.KbIndividualFactory;
import com.cyc.kb.KbIndividual;
//import com.cyc.kb.KbTermFactory;
import com.cyc.kb.exception.CreateException;
import com.cyc.kb.exception.KbTypeException;
import com.cyc.session.CycSessionManager;
import com.cyc.session.exception.SessionException;
import java.util.Set;
 
public class QueryTest {
    //Author: Reid Paape. 
    //Licence: CC0
    public static  void main(String[] argv){
        for (String arg: argv) {
            try {
                CycSessionManager.getCurrentSession().getOptions().setDefaultContext(
                ContextFactory.getDefaultContext(ContextFactory.UV_MT, ContextFactory.INFERENCE_PSC));
                   
    //            KbCollection planetInTheSolarSystem = KbCollectionFactory.get("PlanetInTheSolarSystem");
                if (KbCollectionFactory.existsAsType(arg)) {
                    KbCollection collection = KbCollectionFactory.get(arg);
                    Set<KbIndividual> indivs = (Set<KbIndividual>) collection.getInstances();
                    Set<KbCollection> gens   = (Set<KbCollection>) collection.getGeneralizations();
                    Set<KbCollection> specs  = (Set<KbCollection>) collection.getSpecializations();

                    System.out.println(
                        "*****************************************************\n" +
                        arg + " is a collection\n\n"
                    );

                    System.out.println("individuals");
                    for (KbIndividual indiv: indivs) {
                        System.out.println(
                            "toString:     " + indiv.toString()    + "\n" +
                            "getComments:  " + indiv.getComments() + "\n"
                        );
                    }

                    System.out.println("generalizations");
                    for (KbCollection gen: gens) {
                        System.out.println(
                            "toString:   " + gen.toNlString()    + "\n" +
                            "getComments:  " + gen.getComments()   + "\n"
                        );
                    }

                    System.out.println("specializations");
                    for (KbCollection spec: specs) {
                        System.out.println(
                            "toString:     " + spec.toString()     + "\n" +
                            "getComments:  " + spec.getComments()  + "\n"
                        );
                    }

                    System.out.println(
                        "*****************************************************\n"
                    );

                }

                if (KbIndividualFactory.existsAsType(arg)) {
                    KbIndividual individual = KbIndividualFactory.get(arg);
                        System.out.println(
                            "toString:     " + individual.toString()     + "\n" +
                            "getComments:  " + individual.getComments()  + "\n"
                        );
                }

                String word = arg + "-TheWord";
                if (KbIndividualFactory.existsAsType(word)) {
                    KbIndividual lexicalWord = KbIndividualFactory.get(word);
                        System.out.println(
                            "toString:     " + lexicalWord.toString()     + "\n" +
                            "getComments:  " + lexicalWord.getComments()  + "\n"
                        );
                }
            } catch (KbTypeException | CreateException | SessionException e) {
                System.out.println(
                    "*****************************************************\n" +
                    "A problem occured while fetching KbTerm:\n  "            +
                     arg                                             + "\n\n" +
                     e                                                 + "\n" +
                    "*****************************************************\n"
                );
            }
        }
    }
}