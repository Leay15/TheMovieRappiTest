TheMovieDB Rappi Test

Este proyecto se encuentra desarrollado bajos la arquitectura MVP utilizando principios de Clean

Cada modulo se encuentra separado del resto y dentro de cada paquete se encuentran las capas de la arquitectura MVP.

En el caso de las vistas se encuentra dividido en:
 -component. Se encuentra la configuracion del componente para la inyeccion de dependencias
 -modules. Contiene la configuracion de las clases que pueden ser inyectadas
 -presentation. Contiene la abstraccion de la presentacion de datos
    -presenter. Dentro se encuentran la abstraccion de los presentadores de datos y sus implementaciones. Las clases se encargan de recibir y enviar notificaciones de la vista para obtener datos y que sean mostrados en ella
    -interactor. Dentro se encuentra la abstraccion de los interactores y sus implementaciones. Las clases se encargan de recibir la peticion del presentador para obtener los datos y que sean regresados al presentador.
 -view. Contiene los view que son mostrados al usuario, asi como componentes necesarios para mostrar la vista
 -viewModels. Contiene la definicion de los metodos corresspondiente a las vistas

 En el caso de la capa de red se encuentra dividido en:
 -model. Se encuentran los modelos de respuesta del servicio
 -module. Configuracion de clases que pueden ser inyectadas correspondientes al consumo de red
 -retrofit. Contiene las interfaces que provee el consumo de servicios de red
 *DataConfiguration. Clase que contiene la definicion de valores necesarios para el consumo como Endpoints y Api_key

 En el caso de la capa de persistencia de datos se encuentra dividido en:
 -model. Son los modelos de entidades(tablas) y objetos(datos) que seran guardados en persistencia
 -module. Contiene la configuracion para poder inyectar el manejador de base de datos Room
 -room. Contiene las interfaces del DAO para definir las operaciones a realizar en cada caso y DBO para configurar las acciones en base a los DAO
 -typeConverters. La clase presente provee los metodos para la serializacion de datos en caso de ser un tipo de dato complejo.


El principio de responsabilidad unica, consiste en dotar a una clase unicamente de una responsabilidad en particular, por ejemplo, en el caso de los presentadores unicamente se encargan de recibir la notificacion de la vista de
un evento en particular para enviar la notificacion de obtencion de los datos, espera a recibirlos para tratarlos y devolverlos a la vista para que sean mostrados, en ningun momento le importa saber como mostrara los datos o como estan siendo obtenidos.

Un buen codigo debe poder ser entendido facil y rapidamente por cualquier otro programador que tome el codigo, debe utilizarse los principios de abstraccion y
responsabilidad unica y nombramiento descriptivo para que en caso de presentarse una correccion, sea rapidamente deducido de donde proviene el error.
Si los datos se estan obteniendo mal seria de los interactores o datasource.
Si se estan presentando mal los datos o mal tratados seria de los presentadores.
Si los datos se muestran de forma indebida, el caso de la vista.