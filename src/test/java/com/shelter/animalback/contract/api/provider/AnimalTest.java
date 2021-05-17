package com.shelter.animalback.contract.api.provider;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.shelter.animalback.controller.AnimalController;
import com.shelter.animalback.domain.Animal;
import com.shelter.animalback.service.interfaces.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@PactBroker(authentication = @PactBrokerAuth(token = "${PACT_BROKER_TOKEN}"), url = "${PACT_BROKER_URL}")
@Provider("AnimalShelterBack")
@ExtendWith(MockitoExtension.class)
public class AnimalTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {

        context.verifyInteraction();
    }

    @BeforeEach
    public void changeTargetContext(PactVerificationContext context) {
        MockMvcTestTarget testTarget = new MockMvcTestTarget();
        testTarget.setControllers(animalController);
        context.setTarget(testTarget);
    }

    @State("has animals")
    public void verifyListAnimals() {
        Animal animal = new Animal();
        animal.setName("Manchas");
        animal.setBreed("Bendali");
        animal.setGender("Female");
        animal.setVaccinated(true);

        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(animal);

        Mockito.when(animalService.getAll()).thenReturn(animals);
    }

    @State("delete animal")
    public void verifyDeleteAnimal() {
        Mockito.doNothing().when(animalService).delete(Mockito.anyString());
    }

    @State("create animal")
    public void verifyCreateAnimal(){
        Animal animal = new Animal();
        animal.setName("Other Animal");
        animal.setBreed("Bendali");
        animal.setGender("Female");
        animal.setVaccinated(true);

        Mockito.when(animalService.save(Mockito.any(Animal.class))).thenReturn(animal);
    }
}