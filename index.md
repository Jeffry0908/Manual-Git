<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE composition PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<ui:composition xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
                template="./WEB-INF/plantilla.xhtml"
                xmlns:b="http://bootsfaces.net/ui"
                xmlns:f="http://xmlns.jcp.org/jsf/core"
                xmlns:h="http://xmlns.jcp.org/jsf/html">
    <ui:define name="content">
        <b:form>
            <b:carousel id="tiovivo" style="width:100%" interval="2500">
              <b:carouselItem >
                <img src="../resources/img/libreria1.jpg" alt="Valladolid" style="width: 100%; height: 350px" />
                <b:carouselCaption>
                  <h2>¡Bienvenidos A La Libreria Jahveh!</h2>
                  <p>Venta de papeleria y articulos escolares <a style="color:#fff" href="http://www.vanderkrogt.net/statues/object.php?webpage=CO&record;=escl010"></a>.</p>
                </b:carouselCaption>
              </b:carouselItem>
              <b:carouselItem>
                <img src="../resources/img/libreria2.jpg" alt="Valladolid" style="width: 100%; height: 350px" />
              </b:carouselItem>
              <b:carouselItem>
                <img src="../resources/img/libreria3.jpg" alt="Valladolid" style="width: 100%; height: 350px" />
              </b:carouselItem>
              <b:carouselItem>
                <img src="../resources/img/libreria4.jpg" alt="Valladolid" style="width: 100%; height: 350px" />
                <b:carouselCaption>
                  <h2></h2>
                  <p></p>
                </b:carouselCaption>
              </b:carouselItem>
            </b:carousel>
        </b:form>

            
     <b:column col-sm="6" col-md="4">
        <b:thumbnail>
              <img src="../resources/img/papeleria3.jpg" class="im" alt="Generic placeholder thumbnail" style="width: 780%;height: 400px;" />
              <f:facet name="caption">
                    <h3>Productos De Papeleria</h3>
                    <p>Encuentra Productos De Papeleria</p>
                    <p>
                    <b:button look="primary" value="Button"/>
                    <b:button value="Button"/>
                    </p>
              </f:facet>
        </b:thumbnail>
    </b:column>
    <b:column col-sm="6" col-md="4">
        <b:thumbnail>
            <img src="../resources/img/papeleria4.jpg" class="im" alt="Generic placeholder thumbnail" style="width: 780%;height: 400px;" />
            <f:facet name="caption">
              <h3>Informacion De La Libreria Yahveh</h3>
              <p>Encuentra Informacion Acerca De Nosotros Aca</p>
              <p>
              <b:button look="primary" value="Button"/>
              <b:button value="Button"/>
              </p>
            </f:facet>
        </b:thumbnail>
    </b:column>
    <b:column col-sm="6" col-md="4">
        <b:thumbnail>
            <img src="../resources/img/banne.jpg" class="im" alt="Generic placeholder thumbnail" style="width: 780%;height: 400px;" />
            <f:facet name="caption">
              <h3>Informacion De La Libreria Yahveh</h3>
              <p>Encuentra Informacion Acerca De Nosotros Aca</p>
              <p>
              <b:button look="primary" value="Button"/>
              <b:button value="Button"/>
              </p>
            </f:facet>
        </b:thumbnail>
    </b:column>
    </ui:define>
    </ui:composition>
