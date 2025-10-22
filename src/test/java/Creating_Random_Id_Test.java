import org.example.Creating_Random_Id;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creating_Random_Id_Test {
    Creating_Random_Id cri = new Creating_Random_Id();
    @Test
    public void rnd() {
        int number1 = 1;
        int number2 = 28;
        int result = cri.rnd(number1, number2);
        assertEquals(true , number1 <= result && result<= number2);
    }
    @Test
    public void character_selection(){
        assertEquals(1,cri.character_selection().length() );
    }
    @Test
    public void generation_id(){
        int id_length = 20;
        assertEquals(id_length ,cri.generation_id().length() );
    }
}
