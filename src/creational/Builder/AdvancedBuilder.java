package creational.Builder;

//todas essas classes eram públicas mas como agora elas estão no mesmo arquivo, para serem chamadas de public precisaria renomear o arquivo.
// O DTO aqui é meio cagativo pq os dados que transitam são criados na hora, ao invés de serem sacados de uma BD, então não é últil ter um DTO aqui.
// Caso os dados que fossem utilizados  na UI não fossem exatamente os mesmo que são lidos, teriamos um isolamento que garante proteção dos dados

import java.time.LocalDate;
import java.time.Period;

/**
 * produto composto: é composto pq é usado no User
 */
class Address {

    private String houseNumber;

    private String street;

    private String city;

    private String zipcode;

    private String state;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}


/**
 * produto
 */
class User {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

}


/**
 * creational.Builder interface
 * como não trabalhamos diretamente com um objeto e sim com Data Transfer Object (DTO) eles guardam os dados do objeto e são usados para as validações
 */
interface UserDTO { //como o user é composto por nome e sobrenome, devemos concatenar isso no dto

    String getName();

    String getAddress();

    String getAge();
}

/**
 * Concrete builder
 */
class UserWebDTO implements UserDTO {

    private String name;

    private String address;

    private String age;

    public UserWebDTO(String name, String address, String age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "name=" + name + "\nage=" + age + "\naddress=" + address ;
    }

}


/**
 *  Abstract builder - essa e a de baixo que realmente importam para o programa. As duas de cima são camada de transporte
 */
interface UserDTOBuilder {
    //methods to build "parts" of product at a time. o "TIPO" é o mesmo nome da interface pq permite posteriormente concatenar as chamadas do metodo pois eles retornam sempre o mesmo obj
    UserDTOBuilder withFirstName(String fname) ;

    UserDTOBuilder withLastName(String lname);

    UserDTOBuilder withBirthday(LocalDate date);

    UserDTOBuilder withAddress(Address address);
    //method to "assemble" final product
    UserDTO build();
    //(optional) method to fetch already built object
    UserDTO getUserDTO();
}


/**
 * The concrete builder for UserWebDTO
 */

class UserWebDTOBuilder implements UserDTOBuilder {

    private String firstName;
    private String lastName;
    private String age;
    private String address;
    private UserWebDTO dto; //isso é só para transporte

    @Override
    public UserDTOBuilder withFirstName(String fname) {
        firstName = fname;
        return this;
    }

    @Override
    public UserDTOBuilder withLastName(String lname) {
        lastName = lname;
        return this;
    }

    @Override
    public UserDTOBuilder withBirthday(LocalDate date) {
        Period ageInYears = Period.between(date, LocalDate.now());
        age = Integer.toString(ageInYears.getYears());
        return this;
    }

    @Override
    public UserDTOBuilder withAddress(Address address) {
        this.address = address.getHouseNumber() +", " + address.getStreet()
                +"\n" + address.getCity() +"\n"+address.getState()+" "+address.getZipcode();
        return this;
    }

    @Override
    public UserDTO build() {
        dto = new UserWebDTO(firstName+ " "+lastName, address, age); // se cria um objeto e o retorna para podermos ter acesso na função opcional
        return dto;
    }

    @Override
    public UserDTO getUserDTO() {
        return dto;
    }

}


class Client {

    public static void main(String[] args) {
        User user = createUser();//payload de dados
        UserDTOBuilder builder = new UserWebDTOBuilder();// interfaceBuilder nome = new ConcreteBuilder  é usado para desacoplar código
        //Client has to provide director with concrete builder
        UserDTO dto = directBuild(builder, user);//boa práricas _ não se trafega diretamente os dados do objeto
        System.out.println(dto);
    }

    /**
     * This method serves the role of director in builder pattern.
     */
    private static UserDTO directBuild(UserDTOBuilder builder, User user) {
        return builder.withFirstName(user.getFirstName()).withLastName(user.getLastName())
                .withAddress(user.getAddress())
                .withBirthday(user.getBirthday())
                .build();
    }

    /**
     * Returns a sample user.
     */
    public static User createUser() {
        User user = new User();
        user.setBirthday(LocalDate.of(1960, 5, 6));
        user.setFirstName("Ron");
        user.setLastName("Swanson");
        Address address = new Address();
        address.setHouseNumber("100");
        address.setStreet("State Street");
        address.setCity("Pawnee");
        address.setState("Indiana");
        address.setZipcode("47998");
        user.setAddress(address);
        return user;
    }
}