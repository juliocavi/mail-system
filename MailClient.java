/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    
    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = new MailServer();
        this.server = server;
        this.user = user;
    }

       /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextItemAndSendReplay()
    {
        //creamos una nueva variable local de tipo MailItem
        //en ella alamcenamos el siguiente mensaje de nuestro perfil,
        //con el metodo getgetNextMailItem(user) referenciando nuestro perfil con el parametro user
        MailItem item = server.getNextMailItem(user);
        //si tenemos nuevo mensajes, 
        //creamos una nueva variable local llamada Respuesta, que almacenara un mensaje de respuesta, 
        //en ella almacenaremos los datos del remitente y la respuesta automatica
        //este nuevo mensaje de respuesta se va almacenar en el servidor
        //HE DECIDIDO LLAMAR AL METODO PRINT DE ITEM PARA VISUALIZAR ESA RESPUESTA
        if(item != null){
            String destino = item.getFrom();
            String asunto = "RE: " + item.getSubject();
            String mensaje = "\nRespuesta automatica.\nGracias " + item.getFrom() 
            + ".\nMensaje de " + item.getFrom() + ": " + item.getMessage();
            MailItem Respuesta = new MailItem(user, destino, asunto, mensaje);
            server.post(Respuesta);
        }
        //si no hay nuevos mensajes, me sacas un mensaje por pantalla avisando de ello
        else{
            System.out.println("No messages");
        }
        return item;
    }
      
    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        return server.getNextMailItem(user);
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
    }
}
