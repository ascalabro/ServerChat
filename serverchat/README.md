ServerChat
==========

Java Chat Server/Client for LAN

To run a server with default port,
$ java serverchat/ServerChat_Main server

To run a server with specified port,
$ java serverchat/ServerChat_Main server <optional>port

To run a client to connect to the server,
$ java serverchat/ServerChat_Main 

You need to set the SERVER_IP property within ServerChat_Main.java to 
the IP address of the server machine then rebuild.
After the client is connected, text can be received both ways.
