/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "CheckoutController")
@SessionScoped

public class CheckoutController implements Serializable {

    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    private String provincia;
    private String Canton;
    private String Distrito;

    private String countryGroup;
    private List<SelectItem> countriesGroup;

    private String Nombre;
    private String Primer_Apellido;
    private String Segundo_Apellido;
    private String Correo_Electronico;
    private String Telefono;
    private String Direccion;
    private String hora;
    private String Fecha;

    private String selection;
    private List<SelectItem> countries;

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

    public String getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(String countryGroup) {
        this.countryGroup = countryGroup;
    }

    public List<SelectItem> getCountriesGroup() {
        return countriesGroup;
    }

    public void setCountriesGroup(List<SelectItem> countriesGroup) {
        this.countriesGroup = countriesGroup;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String Canton) {
        this.Canton = Canton;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public CheckoutController() {
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String Provincia) {
        this.provincia = Provincia;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void Salir() {

        loginController.setClienteTO(null);
        loginController.setPass(null);
        loginController.setUser(null);

        this.redireccionar("/faces/index.xhtml");
    }

    public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String Primer_Apellido) {
        this.Primer_Apellido = Primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String Segundo_Apellido) {
        this.Segundo_Apellido = Segundo_Apellido;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String Correo_Electronico) {
        this.Correo_Electronico = Correo_Electronico;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

//    public void LocationFormat(){
//        this.selection;
//    }
    @PostConstruct
    public void init() {

        countriesGroup = new ArrayList<>();

        SelectItemGroup San_Jose = new SelectItemGroup("Canton: San Jose");
        San_Jose.setSelectItems(new SelectItem[]{
            new SelectItem("Carmen", "Carmen"),
            new SelectItem("Merced", "Merced"),
            new SelectItem("Hospital", "Hospital"),
            new SelectItem("Catedral", "Catedral"),
            new SelectItem("Zapote", "Zapote"),
            new SelectItem("San Francisco de Dos Ríos", "San Francisco de Dos Ríos"),
            new SelectItem("La Uruca", "La Uruca"),
            new SelectItem("Mata Redonda", "Mata Redonda"),
            new SelectItem("Pavas", "Pavas"),
            new SelectItem("Hatillo", "Hatillo"),
            new SelectItem("San Sebastián", "San Sebastián")});
        countriesGroup.add(San_Jose);

        SelectItemGroup Escazú = new SelectItemGroup("Canton: Escazú");
        Escazú.setSelectItems(new SelectItem[]{
            new SelectItem("Escazú", "Escazú"),
            new SelectItem("San Antonio", "San Antonio"),
            new SelectItem("San Rafael", "San Rafael")

        });
        countriesGroup.add(Escazú);

        SelectItemGroup Desamparados = new SelectItemGroup("Canton: Desamparados");
        Desamparados.setSelectItems(new SelectItem[]{
            new SelectItem("Desamparados", "Desamparados"),
            new SelectItem("San Miguel", "San Miguel"),
            new SelectItem("San Juan de Dios", "San Juan de Dios"),
            new SelectItem("San Juan de Dios", "San Juan de Dios"),
            new SelectItem("San Rafael Arriba", "San Rafael Arriba"),
            new SelectItem("San Antonio", "San Antonio"),
            new SelectItem("Frailes", "Frailes"),
            new SelectItem("Patarrá", "Patarrá"),
            new SelectItem("San Cristóbal", "San Cristóbal"),
            new SelectItem("Rosario", "Rosario"),
            new SelectItem("Damas", "Damas"),
            new SelectItem("San Rafael Abajo", "San Rafael Abajo"),
            new SelectItem("Gravilias", "Gravilias"),
            new SelectItem("Los Guido", "Los Guido"),});

        countriesGroup.add(Desamparados);

        SelectItemGroup Puriscal = new SelectItemGroup("Canton: Puriscal");
        Puriscal.setSelectItems(new SelectItem[]{
            new SelectItem("Santiago", "Santiago"),
            new SelectItem("Mercedes Sur", "Mercedes Sur"),
            new SelectItem("Barbacoas", "Barbacoas"),
            new SelectItem("Grifo Alto", "Grifo Alto"),
            new SelectItem("San Rafael", "San Rafael"),
            new SelectItem("Candelarita", "Candelarita"),
            new SelectItem("Desamparaditos", "Desamparaditos"),
            new SelectItem("San Antonio", "San Antonio"),
            new SelectItem("Chires", "Chires")

        });
        countriesGroup.add(Puriscal);

        SelectItemGroup Tarrazú = new SelectItemGroup("Canton: Tarrazú");
        Tarrazú.setSelectItems(new SelectItem[]{
            new SelectItem("San Marcos", "San Marcos"),
            new SelectItem("San Lorenzo", "San Lorenzo"),
            new SelectItem("San Carlos", "San Carlos")

        });

        countriesGroup.add(Tarrazú);

        SelectItemGroup Aserrí = new SelectItemGroup("Canton: Aserrí");
        Aserrí.setSelectItems(new SelectItem[]{
            new SelectItem("Aserrí", "Aserrí"),
            new SelectItem("Tarbaca", "Tarbaca"),
            new SelectItem("Vuelta de Jorco", "Vuelta de Jorco"),
            new SelectItem("San Gabriel", "San Gabriel"),
            new SelectItem("Legua", "Legua"),
            new SelectItem("Monterrey", "Monterrey"),
            new SelectItem("Salitrillos", "Salitrillos"),});

        countriesGroup.add(Aserrí);

        SelectItemGroup Mora = new SelectItemGroup("Canton: Mora");
        Mora.setSelectItems(new SelectItem[]{
            new SelectItem("Colón", "Colón"),
            new SelectItem("Guayabo", "Guayabo"),
            new SelectItem("Tabarcia", "Tabarcia"),
            new SelectItem("Piedras Negras", "Piedras Negras"),
            new SelectItem("Picagres", "Picagres"),
            new SelectItem("Jaris", "Jaris"),
            new SelectItem("Quitirrisí", "Quitirrisí"),});

        countriesGroup.add(Mora);

        SelectItemGroup Goicoechea = new SelectItemGroup("Canton: Goicoechea");
        Goicoechea.setSelectItems(new SelectItem[]{
            new SelectItem("Guadalupe", "Guadalupe"),
            new SelectItem("San Francisco", "San Francisco"),
            new SelectItem("Calle Blancos", "Calle Blancos"),
            new SelectItem("Mata de Plátano", "Ipís"),
            new SelectItem("Rancho Redondo", "Rancho Redondo"),
            new SelectItem("Purral", "Purral")
        });
        countriesGroup.add(Goicoechea);

        SelectItemGroup Santa_Ana = new SelectItemGroup("Canton: Santa Ana");
        Santa_Ana.setSelectItems(new SelectItem[]{
            new SelectItem("Santa Ana", "Santa Ana"),
            new SelectItem("Salitral", "Salitral"),
            new SelectItem("Pozos", "Pozos"),
            new SelectItem("Uruca", "Uruca"),
            new SelectItem("Piedades", "Piedades"),
            new SelectItem("Brasil", "Brasil")
        });
        countriesGroup.add(Santa_Ana);

        SelectItemGroup Alajuelita = new SelectItemGroup("Canton: Alajuelita");
        Alajuelita.setSelectItems(new SelectItem[]{
            new SelectItem("Alajuelita", "Alajuelita"),
            new SelectItem("San Josecito", "San Josecito"),
            new SelectItem("San Antonio", "San Antonio"),
            new SelectItem("Concepción", "Concepción"),
            new SelectItem("San Felipe", "San Felipe")
        });
        countriesGroup.add(Alajuelita);

        SelectItemGroup Vázquez_de_Coronado = new SelectItemGroup("Canton: Vázquez de Coronado");
        Vázquez_de_Coronado.setSelectItems(new SelectItem[]{
            new SelectItem("San Isidro", "San Isidro"),
            new SelectItem("San Rafael", "San Rafael"),
            new SelectItem("Dulce Nombre de Jesús", "Dulce Nombre de Jesús"),
            new SelectItem("Patalillo", "Patalillo"),
            new SelectItem("Cascajal", "Cascajal")
        });
        countriesGroup.add(Vázquez_de_Coronado);

        SelectItemGroup Acosta = new SelectItemGroup("Canton: Acosta");
        Acosta.setSelectItems(new SelectItem[]{
            new SelectItem("San Ignacio", "San Ignacio"),
            new SelectItem("Guaitil", "Guaitil"),
            new SelectItem("Palmichal", "Palmichal"),
            new SelectItem("Cangrejal", "Cangrejal"),
            new SelectItem("Sabanillas", "Sabanillas")
        });
        countriesGroup.add(Acosta);

        SelectItemGroup Tibás = new SelectItemGroup("Canton:  Tibás");
        Tibás.setSelectItems(new SelectItem[]{
            new SelectItem("Anselmo llorente", "Anselmo llorente"),
            new SelectItem("León XIII", "León XIII"),
            new SelectItem("Colima", "Colima")
        });
        countriesGroup.add(Tibás);

        SelectItemGroup Moravia = new SelectItemGroup("Canton: Moravia");
        Moravia.setSelectItems(new SelectItem[]{
            new SelectItem("San Vicente", "San Vicente"),
            new SelectItem("San Jerónimo", "San Jerónimo"),
            new SelectItem("La Trinidad", "La Trinidad"),
            new SelectItem("Cangrejal", "Cangrejal"),
            new SelectItem("Sabanillas", "Sabanillas")
        });
        countriesGroup.add(Moravia);

        SelectItemGroup Montes_de_Oca = new SelectItemGroup("Canton: Montes de Oca");
        Montes_de_Oca.setSelectItems(new SelectItem[]{
            new SelectItem("San Pedro", "San Pedro"),
            new SelectItem("Sabanilla", "Sabanilla"),
            new SelectItem("Mercedes", "Mercedes"),
            new SelectItem("San Rafael", "San Rafael")
        });
        countriesGroup.add(Montes_de_Oca);

        SelectItemGroup Turrubares = new SelectItemGroup("Canton: Turrubares");
        Turrubares.setSelectItems(new SelectItem[]{
            new SelectItem("San Pablo", "San Pablo"),
            new SelectItem("San Pedro", "San Pedro"),
            new SelectItem("San Juan de Mata", "San Juan de Mata"),
            new SelectItem("San Luis", "San Luis"),
            new SelectItem("Carara", "Carara")
        });
        countriesGroup.add(Turrubares);

        SelectItemGroup Dota = new SelectItemGroup("Canton: Dota");
        Dota.setSelectItems(new SelectItem[]{
            new SelectItem("Santa María", "Santa María"),
            new SelectItem("Jardín", "Jardín"),
            new SelectItem("Copey", "Copey")

        });
        countriesGroup.add(Dota);
        SelectItemGroup Curridabat = new SelectItemGroup("Canton: Curridabat");
        Curridabat.setSelectItems(new SelectItem[]{
            new SelectItem("Curridabat", "Curridabat"),
            new SelectItem("Granadilla", "Granadilla"),
            new SelectItem("Sánchez", "Sánchez"),
            new SelectItem("Tirrases", "Tirrases")
        });
        countriesGroup.add(Curridabat);
        SelectItemGroup Pérez_Zeledón = new SelectItemGroup("Canton:  Pérez Zeledón");
        Pérez_Zeledón.setSelectItems(new SelectItem[]{
            new SelectItem("San Isidro de El General", "San Isidro de El General"),
            new SelectItem("El General", "El General"),
            new SelectItem("Daniel Flores", "Daniel Flores"),
            new SelectItem("Rivas", "Rivas"),
            new SelectItem("San Pedro", "San Pedro"),
            new SelectItem("Platanares", "Platanares"),
            new SelectItem("Pejibaye", "Pejibaye"),
            new SelectItem("Cajón", "Cajón"),
            new SelectItem("Barú", "Barú"),
            new SelectItem("Río Nuevo", "Río Nuevo"),
            new SelectItem("Páramo", "Páramo"),
            new SelectItem("La Amistad", "La Amistad")});
        countriesGroup.add(Pérez_Zeledón);
        SelectItemGroup León_Cortés_Castro = new SelectItemGroup("Canton: León Cortés Castro");
        León_Cortés_Castro.setSelectItems(new SelectItem[]{
            new SelectItem("San Pablo", "San Pablo"),
            new SelectItem("San Andrés", "San Andrés"),
            new SelectItem("Llano Bonito", "Llano Bonito"),
            new SelectItem("San Isidro", "San Isidro"),
            new SelectItem("Santa Cruz", "Santa Cruz"),
            new SelectItem("San Antonio", "San Antonio"),});
        countriesGroup.add(León_Cortés_Castro);

        SelectItemGroup Cartago = new SelectItemGroup("Canton: Cartago");
        Cartago.setSelectItems(new SelectItem[]{
            new SelectItem("San Pablo", "San Pablo"),
            new SelectItem("San Pedro", "San Pedro"),
            new SelectItem("San Juan de Mata", "San Juan de Mata"),
            new SelectItem("San Luis", "San Luis"),
            new SelectItem("Carara", "Carara"),
            new SelectItem("Oriental", "Oriental"),
            new SelectItem("Occidental", "Occidental"),
            new SelectItem("Carmen", "Carmen"),
            new SelectItem("San Nicolás", "San Nicolás"),
            new SelectItem("Aguacaliente", "Aguacaliente"),
            new SelectItem("Guadalupe", "Guadalupe"),
                        new SelectItem("Corralillo", "Corralillo"),
            new SelectItem("Tierra Blanca", "Tierra Blanca"),
            new SelectItem("Dulce Nombre", "Dulce Nombre"),
            new SelectItem("Llano Grande", "Llano Grande"),
            new SelectItem("Quebradilla", "Quebradilla")
            
            
        });
        countriesGroup.add(Cartago);

        
         SelectItemGroup Paraíso = new SelectItemGroup("Canton: Paraíso");
        Paraíso.setSelectItems(new SelectItem[]{
            new SelectItem("Paraíso", "Paraíso"),
            new SelectItem("Santiago", "Santiago"),
            new SelectItem("Orosi", "Orosi"),
            new SelectItem("Cachí", "Cachí"),
            new SelectItem("Llanos de Santa Lucía", "Llanos de Santa Lucía"),
            
            
            
        });
        countriesGroup.add(Paraíso);
        
        SelectItemGroup La_Unión = new SelectItemGroup("Canton: La Unión");
        La_Unión.setSelectItems(new SelectItem[]{
            new SelectItem("Tres Ríos", "Tres Ríos"),
            new SelectItem("San Diego", "San Diego"),
            new SelectItem("San Juan", "San Juan"),
            new SelectItem("San Rafael", "San Rafael"),
            new SelectItem("Concepción", "Concepción"),
                        new SelectItem("Dulce Nombre", "Dulce Nombre"),
            new SelectItem("San Ramón", "San Ramón"),
            new SelectItem("Río Azul", "Río Azul")
         
        });
        countriesGroup.add(La_Unión);
        
         SelectItemGroup Jiménez = new SelectItemGroup("Canton: Jiménez");
        Jiménez.setSelectItems(new SelectItem[]{
            new SelectItem("Juan Viñas", "Juan Viñas"),
            new SelectItem("Tucurrique", "Tucurrique"),
            new SelectItem("Pejibaye", "Pejibaye"),
            new SelectItem("La Victoria", "La Victoria"),
            
        });
        countriesGroup.add(Jiménez);
        
//        countries = new ArrayList<>();
        //provincias
//        SelectItemGroup Alajuela = new SelectItemGroup("Alajuela");
        //Australia
//        SelectItemGroup Australia = new SelectItemGroup("Australia");
//        Place Australia_info = new Place("Australia","au","country");
//        Australia.setValue(Australia_info);
//
//        SelectItemGroup group11 = new SelectItemGroup("New South Wales");
//        group11.setValue(new Place("New South Wales", null, "states"));
//
//        SelectItemGroup group12 = new SelectItemGroup("Queensland");
//        group12.setValue(new Place("Queensland", null, "states"));
//
//        Australia.setSelectItems(new SelectItem[]{group11, group12});
//        SelectItem option111 = new SelectItem(new Place("Sydney", null, "city"), "Sydney");
//
////        SelectItem option112 = new SelectItem(new Place("Newcastle", null, "city"), "NewCastle");
//        SelectItem option112 = new SelectItem("NewCastle");
//
//        SelectItem option113 = new SelectItem(new Place("Wollongong", null, "city"), "Wollongong");
//        
//        group11.setSelectItems(new SelectItem[]{option111, option112, option113});
//
//        countries.add(Australia);
//
//        SelectItemGroup group2 = new SelectItemGroup("Canada");
//        group2.setValue(new Place("Canada", "ca", "country"));
//
//        SelectItemGroup group3 = new SelectItemGroup("United States");
//        group3.setValue(new Place("United States", "us", "country"));
//
//        SelectItemGroup group21 = new SelectItemGroup("Quebec");
//        group21.setValue(new Place("Quebec", null, "states"));
//        SelectItemGroup group22 = new SelectItemGroup("Ontario");
//        group22.setValue(new Place("Ontario", null, "states"));
//
//        SelectItemGroup group31 = new SelectItemGroup("California");
//        group31.setValue(new Place("California", null, "states"));
//        SelectItemGroup group32 = new SelectItemGroup("Florida");
//        group32.setValue(new Place("Florida", null, "states"));
//        SelectItemGroup group33 = new SelectItemGroup("Texas");
//        group33.setValue(new Place("Texas", null, "states"));
//
//        SelectItem option121 = new SelectItem(new Place("Brisbane", null, "city"));
//        SelectItem option122 = new SelectItem(new Place("Townsville", null, "city"));
//        group12.setSelectItems(new SelectItem[]{option121, option122});
//
//        SelectItem option211 = new SelectItem(new Place("Montreal", null, "city"));
//        SelectItem option212 = new SelectItem(new Place("Quebec City", null, "city"));
//        group21.setSelectItems(new SelectItem[]{option211, option212});
//
//        SelectItem option221 = new SelectItem(new Place("Ottawa", null, "city"));
//        SelectItem option222 = new SelectItem(new Place("Toronto", null, "city"));
//        group22.setSelectItems(new SelectItem[]{option221, option222});
//
//        SelectItem option311 = new SelectItem(new Place("Los Angeles", null, "city"));
//        SelectItem option312 = new SelectItem(new Place("San Diego", null, "city"));
//        SelectItem option313 = new SelectItem(new Place("San Francisco", null, "city"));
//        group31.setSelectItems(new SelectItem[]{option311, option312, option313});
//
//        SelectItem option321 = new SelectItem(new Place("Jacksonville", null, "city"));
//        SelectItem option322 = new SelectItem(new Place("Miami", null, "city"));
//        SelectItem option323 = new SelectItem(new Place("Tampa", null, "city"));
//        SelectItem option324 = new SelectItem(new Place("Orlando", null, "city"));
//        group32.setSelectItems(new SelectItem[]{option321, option322, option323, option324});
//
//        SelectItem option331 = new SelectItem(new Place("Austin", null, "city"));
//        SelectItem option332 = new SelectItem(new Place("Dallas", null, "city"));
//        SelectItem option333 = new SelectItem(new Place("Houston", null, "city"));
//        group33.setSelectItems(new SelectItem[]{option331, option332, option333});
//
//        group2.setSelectItems(new SelectItem[]{group21, group22});
//        group3.setSelectItems(new SelectItem[]{group31, group32, group33});
//
//        countries.add(group2);
//        countries.add(group3);
    }

    public void onItemSelect(SelectEvent event) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected City", event.getObject() + "\n");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SelectItem> getCountries() {
        return countries;
    }

}
