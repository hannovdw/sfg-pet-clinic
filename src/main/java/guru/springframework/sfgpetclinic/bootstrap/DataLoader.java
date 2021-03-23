package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
       if (count == 0) loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType snake = new PetType();
        snake.setName("snake");
        PetType savedSnakePetType = petTypeService.save(snake);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("107 Camelthorn road");
        owner1.setCity("Joburg");
        owner1.setTelephone("082 305 4567");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hanno");
        owner2.setLastName("Smit");
        owner2.setAddress("21 Anaboom singel");
        owner2.setCity("Boksburg");
        owner2.setTelephone("092 234 5678");

        Pet hannosPet = new Pet();
        hannosPet.setPetType(savedCatPetType);
        hannosPet.setOwner(owner2);
        hannosPet.setBirthDate(LocalDate.now());
        hannosPet.setName("Peanut");
        owner2.getPets().add(hannosPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(hannosPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Cat");

        visitService.save(catVisit);

        Owner owner3 = new Owner();
        owner3.setFirstName("Joos");
        owner3.setLastName("van der walt");
        owner3.setAddress("120 Bufalo Thorn Road");
        owner3.setCity("Cape Town");
        owner3.setTelephone("081 345 6789");

        Pet joosPet = new Pet();
        joosPet.setPetType(savedSnakePetType);
        joosPet.setOwner(owner3);
        joosPet.setBirthDate(LocalDate.now());
        joosPet.setName("Suzzy");
        owner3.getPets().add(joosPet);

        ownerService.save(owner3);

        System.out.println("Loaded Owners.........");
        System.out.println("Loaded Pets.........");
        System.out.println("Loaded Visits.........");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);
        vet1.getSpecialities().add(savedRadiology);

        Vet vet2 = new Vet();
        vet2.setFirstName("Giel");
        vet2.setLastName("Mobiel");
        vetService.save(vet2);
        vet2.getSpecialities().add(savedSurgery);

        Vet vet3 = new Vet();
        vet3.setFirstName("Ricky");
        vet3.setLastName("Smit");
        vetService.save(vet3);
        vet3.getSpecialities().add(savedDentistry);

        System.out.println("Loaded Vets.........");
    }
}
