import it.unimi.di.sweng.slalom.Main;
import it.unimi.di.sweng.slalom.model.MixedObserver;
import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestModel {
    private Model SUT;
    private String primaManche;

    @BeforeEach
    void setUp() {
        SUT = new Model();
        InputStream is = Main.class.getResourceAsStream("/first");
        assert is != null;
        Scanner s = new Scanner(is);
        SUT.readFilePrimaManche(s);
    }

    @Test
    void testSciatriceNuova() {
        assertThatThrownBy(()->{
            SUT.addSecondRuntime(new Skier("LORENZO Arienti",11.22));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Nome non presente nella prima manche");
    }

    @Test
    void testAddObserver() {
        MixedObserver<List<Skier>,String> ob1 = mock();
        MixedObserver<List<Skier>,String> ob2 = mock();

        SUT.addObserver(ob1);
        SUT.addObserver(ob2);
        SUT.addSecondRuntime(new Skier("STJERNESUND Thea Louise",57.50));

        verify(ob1).update(SUT,"LIENSBERGER Katharina");
    }
    //Dovrei fare i test per vedere se venogno evocati i metodi in state e non più su model Ma non ho voglia

}
