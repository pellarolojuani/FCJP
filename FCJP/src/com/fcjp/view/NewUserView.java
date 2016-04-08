package com.fcjp.view;

import java.awt.Font;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.servlet.annotation.WebServlet;
import javax.swing.JLabel;
import sun.font.TextLabel;
import com.fcjp.daos.UsuarioDao;
import com.fcjp.entities.Usuario;
import com.fcjp.resources.ConfigurationDataBase;
import com.fcjp.utilities.ClaveHash;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
 
 
@SuppressWarnings("serial")
public class NewUserView extends UI{
      
		TextField nombre;
		TextField apellido;
		TextField email;
       	TextField userTextField;
       	PasswordField passwordTextField;
       	PasswordField repeatPasswordField;
       	VerticalLayout layout;
 
       
       @WebServlet(value = "/newUser", asyncSupported = true)
       @VaadinServletConfiguration(productionMode = false, ui = NewUserView.class)
       public static class Servlet extends VaadinServlet {
       }
      
       @Override
       protected void init(VaadinRequest request) {
              this.layout = new VerticalLayout();
              //layout.setMargin(true);
              this.setSizeFull();
              setContent(layout);
             
            //***************************************************
            ConfigurationDataBase connection = ConfigurationDataBase.newConnection(); 
            connection.newConnection();
            String unaClave = "ClaveDePrueba123";
            ClaveHash hash = new ClaveHash();
            System.out.println("Clave encriptada: " + hash.getClaveEncriptada(unaClave));
            
            Usuario unUsuario = new Usuario(1, "Juan", "Pellarolo", "juanso", hash.getClaveEncriptada(unaClave), "pellarolojuani@yahoo.com.ar");
            UsuarioDao unUsuarioDao = new UsuarioDao();
            unUsuarioDao.setUsuario(unUsuario);
            /*try {
            	unUsuarioDao.insertUser();
            }catch (SQLException e){
            	System.out.println("No se ha podido realizar el insert.");
            }*/
            
            //***************************************************
                                       
              Label textLabel1 = new Label("Crear usuario");
              layout.addComponent(textLabel1);
      
              //Campo para ingresar el usuario
              userTextField = new TextField();
              userTextField.setImmediate(true);
              userTextField.setInputPrompt("Nombre de usuario");
              userTextField.setWidth("300px");
        //updateUserTextFieldCaption(0);
       
        apellido = new TextField();
        apellido.setImmediate(true);
        apellido.setInputPrompt("Apellido");
        apellido.setWidth("300px");
        //updateUserTextFieldCaption(0);
        
        nombre = new TextField();
        nombre.setImmediate(true);
        nombre.setInputPrompt("Nombre completo");
        nombre.setWidth("300px");
        //updateUserTextFieldCaption(0);
        
        email = new TextField();
        email.setImmediate(true);
        email.setInputPrompt("Correo electronico");
        email.setWidth("300px");
       
        //Campo para ingresar la contrasenia
        passwordTextField = new PasswordField();
        passwordTextField.setImmediate(true);
        passwordTextField.setMaxLength(30);
        //updatePasswordFieldCaption(0);
       
        //Campo para repetir la contrasenia
        repeatPasswordField = new PasswordField();
        repeatPasswordField.setImmediate(true);
        repeatPasswordField.setMaxLength(30);
        //updatePasswordFieldCaption(0);
       
        Label passwordLabel = new Label("Password:");
        Label repeatPasswordLabel = new Label("Repeat password:");
       
        Button submitButton = new Button("Submit");
        submitButton.addClickListener(new Button.ClickListener() {
                    
                     public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
                           ClaveHash hash = new ClaveHash();
                    	   layout.addComponent(new Label("Usuario creado exitosamente."));
                           Usuario unUsuario = new Usuario(	1, 
                        		   							nombre.getValue(),
                        		   							apellido.getValue(),
                        		   							userTextField.getValue(), 
                        		   							hash.getClaveEncriptada(passwordTextField.getValue()),
                        		   							email.getValue());
                           UsuarioDao unUsuarioDao = new UsuarioDao();
                           unUsuarioDao.setUsuario(unUsuario);
                           try {
                           	unUsuarioDao.insertUser();
                           }catch (SQLException e){
                           	System.out.println("No se ha podido realizar el insert.");
                           }
                     }
              });
               
        layout.addComponent(nombre);
        layout.addComponent(apellido);
        layout.addComponent(userTextField);
        layout.addComponent(email);
        layout.addComponent(passwordLabel);
        layout.addComponent(passwordTextField);
        layout.addComponent(repeatPasswordLabel);
        layout.addComponent(repeatPasswordField);
        layout.addComponent(submitButton);
       
        layout.setSpacing(true);
       
        layout.setMargin(new MarginInfo(true, true, true, false));
              layout.setSizeUndefined();
              layout.setComponentAlignment(submitButton, Alignment.MIDDLE_CENTER);
       
              // The view root layout
              //VerticalLayout viewLayout = new VerticalLayout(layout);
              //viewLayout.setSizeFull();
              ///viewLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
              //viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
              //setCompositionRoot(viewLayout);
             
 
        userTextField.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(final TextChangeEvent event) {
                updateUserTextFieldCaption(event.getText().length());
            }
        });
 
     }
      
       public VerticalLayout getLayout() {
              return layout;
       }
 
       public void setLayout(VerticalLayout layout) {
              this.layout = layout;
       }
 
       //Updates the sample caption
    private void updatePasswordFieldCaption(final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append(textLength);
        if (passwordTextField.getMaxLength() >= 0) {
            builder.append("/").append(passwordTextField.getMaxLength());
        }
        builder.append(" characters");
        passwordTextField.setCaption(builder.toString());
       
        passwordTextField.addValueChangeListener(new ValueChangeListener() {
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
 
                     @Override
                     public void valueChange(
                                  com.vaadin.data.Property.ValueChangeEvent event) {
                           // TODO Auto-generated method stub
                          
                     }
        });
    }
 
       //Updates the userTextField caption
    private void updateUserTextFieldCaption(final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(textLength));
        if (userTextField.getMaxLength() >= 0) {
            builder.append("/").append(userTextField.getMaxLength());
        }
        builder.append(" characters");
        userTextField.setCaption(builder.toString());
       
        userTextField.addValueChangeListener(new ValueChangeListener() {
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
 
                     @Override
                     public void valueChange(
                                  com.vaadin.data.Property.ValueChangeEvent event) {
                           // TODO Auto-generated method stub
                          
                     }
        });
    }
             
}