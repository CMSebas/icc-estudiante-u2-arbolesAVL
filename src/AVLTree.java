

public class AVLTree{
    
    
    private Node root;
    

    public AVLTree(){
        this.root=null;
    }

    public int height(Node node){


        if(node==null){
            return 0;
        }
        return node.getHeight();
    }

    public int getBalance(Node node){
        if(node==null){
            return 0;

        }
        return height(node.getIzquierda())-height(node.getDerecha());
        
    }

    public void insert(int value){
        System.out.println("Nodo a insertar:"+ value);
        root=insertRec(root,value);
    }

    private Node insertRec(Node node,int value){
        if(node==null){
            Node newNode=new Node(value);
            newNode.setHeight(1);
            System.out.println("Nodo insertado: " + value + "  Balance al insertar = " + getBalance(newNode));
            return newNode;

        }

        if(value<node.getValue()){
            node.setIzquierda(insertRec(node.getIzquierda(), value));
        }else if(value>node.getValue()){
            node.setDerecha(insertRec(node.getDerecha(), value));
        }else{
            return node;
        }

        System.out.println("Node actual:"+ node.getValue());

        int altura=1+Math.max(height(node.getIzquierda()),height(node.getDerecha()));
        
        node.setHeight(altura);
        System.out.println("\tAltura="+ node.getHeight());

        int balance=getBalance(node);
        System.out.println("\tBalance"+getBalance(node));
        //caso izquierda-izquierda
        if(balance>1&& value<node.getValue()){
            System.out.println("Rotacion derecha");
            return this.rotacionDerecha(node);

        }

        //caso derechaDerecha

        if(balance<-1&& value> node.getDerecha().getValue()){
            System.out.println("Rotacion Derecha Derecha");
            return this.rotacionIzquierda(node);
        }  

        //Caso izquierda -derecha
        if(balance > 1 && value > node.getIzquierda().getValue()){
            node.setIzquierda(this.rotacionIzquierda(node.getIzquierda()));
            return this.rotacionDerecha(node);
    
        }

        

        //caso derecha-izquierda

        if(balance < -1 && value < node.getDerecha().getValue()){
            System.out.println("Rotacion derecha-izquierda");
            node.setDerecha(this.rotacionDerecha(node.getDerecha()));
            return this.rotacionIzquierda(node);
        }   


        return node;
    }

    public Node rotacionDerecha(Node y) {
        Node x = y.getIzquierda();
        Node T2 = x.getDerecha();
        x.setDerecha(y);
        y.setIzquierda(T2);
        y.setHeight(1 + Math.max(this.height(y.getIzquierda()), this.height(y.getDerecha())));
        x.setHeight(1 + Math.max(this.height(x.getIzquierda()), this.height(x.getDerecha())));
        return x;
    }

    public Node rotacionIzquierda(Node x) {
        Node y = x.getDerecha();
        Node T2 = y.getIzquierda();
        y.setIzquierda(x);
        x.setDerecha(T2);
        x.setHeight(1 + Math.max(this.height(x.getIzquierda()), this.height(x.getDerecha())));
        y.setHeight(1 + Math.max(this.height(y.getIzquierda()), this.height(y.getDerecha())));
        return y;
    }



}