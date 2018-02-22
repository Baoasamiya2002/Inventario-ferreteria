/**
 *
 * @author Samsung RV415
 */
package inventario;
import java.util.Date;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventario2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);		
	int cont=0;
        int cont2=0;
	int i=0;
	int n=0;
	Producto[] contiene= new Producto[50];
        Venta[] tiene= new Venta[50];
        File archivo= new File(".\\Productos.txt");
        File archivo2= new File(".\\Ventas.txt");
        for(int j=0; j<50; j++){
            contiene[j]=new Producto(" "," "," "," ",0,0.0F);
        }
        if(archivo.exists()){
            try{
                Producto pr=new Producto();
                FileInputStream fis= new FileInputStream(".\\Productos.txt");
                ObjectInputStream os1= new ObjectInputStream(fis);
                while(fis.available()>0){
                   pr=(Producto)os1.readObject();
                   contiene[cont].setId(pr.getId());
                   contiene[cont].setNombre(pr.getNombre());
                   contiene[cont].setDescrip(pr.getDescrip());
                   contiene[cont].setUnidad(pr.getUnidad());
                   contiene[cont].setCantidad(pr.getCantidad());
                   contiene[cont].setPrecio(pr.getPrecio());
                   contiene[cont].setPrecioVenta(pr.getPrecioVenta());
                   cont++;
                }
                os1.close();
            }    
            catch(IOException e){
                System.err.println("ERRoR: "+e);
                System.exit(1);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Inventario2.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        }
        for(int j=0; j<50; j++){
            tiene[j]=new Venta(" "," ",0,0.0F,null);
        } 
        if(archivo2.exists()){
            try{
                Venta v=new Venta();
                FileInputStream fis2= new FileInputStream(".\\Ventas.txt");
                ObjectInputStream os2= new ObjectInputStream(fis2);
                while(fis2.available()>0){
                   v=(Venta)os2.readObject();
                   tiene[cont2].setId(v.getId());
                   tiene[cont2].setNombre(v.getNombre());
                   tiene[cont2].setCantidad(v.getCantidad());
                   tiene[cont2].setPrecioVenta(v.getPrecioVenta());
                   tiene[cont2].setFecha(v.getFecha());
                   cont2++;
                }
                os2.close();
            }
            catch(IOException e){
                System.err.println("ERRoR: "+e);
                System.exit(1);
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Inventario2.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
	String res= "si";
	while(res.equals("si")){
            escribirMenu();
            int opc= sc.nextInt();
            switch (opc){
		case 1:
                    sc.nextLine();
                    do{ 
                        System.out.println("*ALTA\nDame el Id del producto");
                        String id= sc.nextLine();
			contiene[cont].setId(id);
			System.out.println("Dame el nombre del producto");
			String no= sc.nextLine();
			contiene[cont].setNombre(no);
			System.out.println("Dame la descripcion del producto");
			String d= sc.nextLine();
			contiene[cont].setDescrip(d);
			System.out.println("Dame la unidad de medida del producto");
			String u=sc.nextLine();
			contiene[cont].setUnidad(u);
			System.out.println("Dame la cantidad del producto");
			int c= sc.nextInt();
			contiene[cont].setCantidad(c);
                        System.out.println("Dame el precio del producto");
                        float p=sc.nextFloat();
                        contiene[cont].setPrecio(p);
                        contiene[cont].setPrecioVenta((p*100)/100);
			System.out.println("El producto se ha dado de alta\n----------------------");
			cont++;
			System.out.println("\n¿Quieres ingresar otro producto?\n	si/no");
			sc.nextLine();
			res= sc.nextLine();
                    } while (res.equals("si"));
                    res="si";
                    i=0;
                    break;
		case 2:
                    System.out.println("*BAJA\nDame el Id del producto");
                    sc.nextLine();
                    String cl= sc.nextLine(); 
                    while(i<cont){
			if(!(contiene[i].getId()).equals(cl)){
                            i++;
                            if(i>=cont){
                                System.out.println("La Id no existe, verifica que sea correcta y vuelve a intentarlo");
                                i=0;
                                sc.nextLine();
                                cl= sc.nextLine();
                            }
			}
			else{
                            n=i;
                            i=cont;
			}
                    }
                    while(n!=cont){
			contiene[n]=contiene[n+1];
			n++;
                    }
                    contiene[cont].setId(" ");
                    contiene[cont].setNombre(" ");
                    contiene[cont].setUnidad(" ");
                    contiene[cont].setDescrip(" ");
                    contiene[cont].setCantidad(0);
                    contiene[cont].setPrecio(0.0F);
                    System.out.println("El producto se ha dado de baja");
                    cont--;
                    res="si";
                    i=0;
                    break;
		case 3:
                    System.out.println("*CAMBIO\nDame el Id del producto");
                    sc.nextLine();
                    cl= sc.nextLine(); 
                    while(i<cont){
			if(!(contiene[i].getId()).equals(cl)){
                            i++;
                            if(i>=cont){
                                System.out.println("La Id no existe, verifica que sea correcta y vuelve a intentarlo");
                                i=0;
                                sc.nextLine();
                                cl= sc.nextLine();
                            }
			}
			else{
                            n=i;
                            i=cont;
			}
                    }
                    i=0;
                    do{ 
                        System.out.println("* ¿Que desea cambiar? \n1= Id\n2= Nombre\n3= Descrip\n4= Unidad\n5= Cantidad\n6= Precio\n");
			opc = sc.nextInt();
                        if(opc==1){
                            System.out.println("Dame la nueva Id");
                            sc.nextLine();
                            cl= sc.nextLine();
                            contiene[n].setId(cl);
			}
			if(opc==2){
                            System.out.println("Dame el nuevo nombre");
                            sc.nextLine();
                            String c= sc.nextLine();
                            contiene[n].setNombre(c);
			}
			if(opc==3){
                            System.out.println("Dame la nueva descripcion");
                            sc.nextLine();
                            String m= sc.nextLine();
                            contiene[n].setDescrip(m);
			}
			if(opc==4){
                            System.out.println("Dame la nueva unidad");
                            sc.nextLine();
                            String p= sc.nextLine();
                            contiene[n].setUnidad(p);
			}
			if(opc==5){
                            System.out.println("Dame la nueva cantidad");
                            sc.nextLine();
                            int l= sc.nextInt();
                            contiene[n].setCantidad(l);
			}
                        if(opc==6){
                            System.out.println("Dame el nuevo precio");
                            sc.nextLine();
                            float p= sc.nextFloat();
                            contiene[n].setPrecio(p);
			}
			System.out.println("¿Desea cambiar algo mas?\n	si/no");
			sc.nextLine();
                        res= sc.nextLine();
                    } while(res.equals("si"));
                    res="si"; 
                    i=0;	
                    break;
                case 4:
                    System.out.println("*MOSTRAR\nLos productos actuales son: ");
                    while(i<cont){
                        System.out.println("\n Id: "+contiene[i].getId()+"\n Nombre: "+contiene[i].getNombre()+"\n Descripcion: "+contiene[i].getDescrip()+"\n Unidad: "+contiene[i].getUnidad()+"\n Cantidad: "+contiene[i].getCantidad()+"\n Precio: $"+contiene[i].getPrecio()+"\n Precio de venta: $"+contiene[i].getPrecioVenta());
                        i++;
                    }
                    res="si";
                    i=0;
                    System.out.println("\n");
                    break;
		case 5:
                    System.out.println("*VENTA");
                    int cont3=cont2;
                    Date fecha = new Date();
                    do{//reparar que se tenga que repitir el segundo producto 2 veces
                        System.out.println("Dame el id del producto");
                        sc.nextLine();
                        cl= sc.nextLine(); 
                        while(i<cont){
                            if(!(contiene[i].getId()).equals(cl)){
                                i++;
                                if(i>=cont){
                                    System.out.println("La Id no existe, verifica que sea correcta y vuelve a intentarlo");
                                    i=0;
                                    sc.nextLine();
                                    cl= sc.nextLine();
                                }
                            }
                            else{
                                n=i;
                                i=cont;
                            }
                        }
                        System.out.println("Dame la cantidad a vender del producto");
                        if(contiene[n].getCantidad()==0){
                            System.out.println("\n\tYa no hay producto, no se puede realizar la venta");
                        }
                        else{
                            if(contiene[n].getCantidad()<=1 && contiene[n].getCantidad()>0){
                                System.out.println("RECORDATORIO: Queda poco producto, piensa en comprar mas producto");
                            }
                            int l= sc.nextInt();
                            if(l>contiene[n].getCantidad()){
                                System.out.println("\n\tNo hay suficiente cantidad del producto");
                            }   
                            else{
                                tiene[cont2].setId(cl);
                                tiene[cont2].setNombre(contiene[n].getNombre());
                                tiene[cont2].setCantidad(l);
                                float p=(((contiene[n].getPrecioVenta()*16)/100)+contiene[n].getPrecioVenta());
                                tiene[cont2].setPrecioVenta((p)*l);
                                tiene[cont2].setFecha(fecha);
                                contiene[n].setCantidad((contiene[n].getCantidad())-1);
                                cont2++;
                            }
                        }
                        i=0;
                        System.out.println("¿Quiere vender otro producto\n	si/no");
                        sc.nextLine();
                        res= sc.nextLine();
                    } while(res.equals("si"));
                    System.out.println("Confirma el/los producto(s) a vender\nFecha: "+tiene[cont2-1].getFecha()+"\nID\tNombre\t\t\t\t\t\t\tCantidad\tPrecio");
                    float total=0;
                    while(cont3<cont2){
                        System.out.println(tiene[cont3].getId()+"\t"+tiene[cont3].getNombre()+"\t\t"+tiene[cont3].getCantidad()+"\t\t"+tiene[cont3].getPrecioVenta());
                        total=total+tiene[cont3].getPrecioVenta();
                        cont3++;
                    }
                    System.out.println("\t\t\t\t\t\t\t\tTOTAL:  "+total+"\nENTER PARA CONFIRMAR");
                    sc.nextLine();
                    res="si";
                    i=0;
                    break;
                case 6:
                    System.out.println("*MOSTRAR VENTRAS\nLas ventas son: ");
                    while(i<cont2){
                        System.out.println("\n Id: "+tiene[i].getId()+"\n Nombre: "+tiene[i].getNombre()+"\n Cantidad: "+tiene[i].getCantidad()+"\n Precio de venta: $"+tiene[i].getPrecioVenta());
                        i++;
                    }
                    res="si";
                    i=0;
                    break;
                case 7:
                    System.out.println("*REPORTE DE VENTAS\nLas ventas de: "+tiene[i].getFecha()+" hasta: "+tiene[cont2-1].getFecha()+" son:");
                    System.out.println("\nFecha\t\t\t\tID\tNombre\t\t\t\t\t\tCantidad\tPrecio");
                    total=0;
                    while(i<cont2){
                        System.out.println(tiene[i].getFecha()+"\t"+tiene[i].getId()+"\t"+tiene[i].getNombre()+"\t\t"+tiene[i].getCantidad()+"\t"+tiene[i].getPrecioVenta());
                        total=total+tiene[i].getPrecioVenta();
                        i++;
                    }
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t-------\n\t\t\t\t\t\t\t\t\t\tLas ventas totales son: "+total);
                    res="si";
                    i=0;
                    break;
                case 8:
                    System.out.println("*REPORTE DE GANACIAS\nLas ganancias de: "+tiene[i].getFecha()+" hasta: "+tiene[cont2-1].getFecha()+" son:");
                    System.out.println("\nFecha\t\t\t\tID\tGanancias");
                    total=0;
                    float p=0;
                    while(i<cont2){
                        p=((tiene[i].getPrecioVenta()*100)/116);
                        System.out.println(tiene[i].getFecha()+"\t"+tiene[i].getId()+"\t"+p);
                        total=total+p;
                        i++;
                    }
                    System.out.println("\t\t\t\t\t---------\n\t     Las ganancias totales son: "+total);
                    res="si";
                    i=0;
                    break;
                case 9:
                    System.out.println("*IVA TRASLADABLE\nEl IVA trasladable de: "+tiene[i].getFecha()+" hasta: "+tiene[cont2-1].getFecha()+" es:");
                    System.out.println("\nFecha\t\t\t\tID\tIVA");
                    total=0;
                    p=0;
                    while(i<cont2){
                        p=(tiene[i].getPrecioVenta())-((tiene[i].getPrecioVenta()*100)/116);
                        System.out.println(tiene[i].getFecha()+"\t"+tiene[i].getId()+"\t"+p);
                        total=total+p;
                        i++;
                    }
                    System.out.println("\t\t\t\t\t---------\n\t\t El IVA trasladable es: "+total);
                    res="si";
                    i=0;
                    break;
                case 10:
                    System.out.println("*COSTO INVENTARIO\nDame el Id del producto");
                    sc.nextLine();
                    cl= sc.nextLine(); 
                    while(i<cont){
			if(!(contiene[i].getId()).equals(cl)){
                            i++;
                            if(i>=cont){
                                System.out.println("La Id no existe, verifica que sea correcta y vuelve a intentarlo");
                                i=0;
                                sc.nextLine();
                                cl= sc.nextLine();
                            }
			}
			else{
                            n=i;
                            i=cont;
			}
                    }
                    System.out.println("Dame la cantidad del producto");
                    sc.nextLine();
                    int l= sc.nextInt();
                    contiene[n].setCantidad((contiene[n].getCantidad())+l);
                    System.out.println("Dame el precio del producto\n**el precio de compra habia sido: "+contiene[n].getPrecio());
                    sc.nextLine();
                    p= sc.nextFloat();
                    contiene[n].setPrecio(((contiene[n].getPrecio())+p)/2);
                    System.out.println("El nuevo precio del producto es: "+contiene[n].getPrecio());///no estoy seguro si ponerlo aqui
                    res="si";
                    i=0;
                    break;
                case 11:
                    res="no";
                    try{
                        if(archivo.exists()){
                            archivo.delete();                               
                        }
                        archivo.createNewFile();
                        FileOutputStream fs= new FileOutputStream(".\\Productos.txt", true);
                        ObjectOutputStream os= new ObjectOutputStream(fs);
                        while(i<cont){
                            os.writeObject(new Producto(contiene[i].getId(), contiene[i].getNombre(), contiene[i].getDescrip(), contiene[i].getUnidad(), contiene[i].getCantidad(), contiene[i].getPrecio()));
                            i++;
                        }
                        os.close();
                        i=0;
                    }
                    catch(IOException e){
                    System.err.println("ERRoR: "+e);
                    System.exit(1);
                    }
                    try{
                        if(archivo2.exists()){
                            archivo2.delete();                               
                        }
                        archivo2.createNewFile();
                        FileOutputStream fs2= new FileOutputStream(".\\Ventas.txt", true);
                        ObjectOutputStream os2= new ObjectOutputStream(fs2);
                        while(i<cont2){
                            os2.writeObject(new Venta(tiene[i].getId(), tiene[i].getNombre(), tiene[i].getCantidad(), tiene[i].getPrecioVenta(), tiene[i].getFecha()));
                            i++;
                        }
                        os2.close();
                    }
                    catch(IOException e){
                    System.err.println("ERRoR: "+e);
                    System.exit(1);
                    }
                    break;
            }
	}
    }
    public static void escribirMenu(){
        System.out.println("**INVENTARIO\nElige una opcion\n1.- Alta\n2.- Baja\n3.- Cambio\n4.- Mostrar\n5.- Venta\n6.- Mostrar ventas\n7.- Reporte ventas\n8.- Reporte de ganancias\n9.- IVA trasladable\n10.- Costo inventario\n11.- Salir");
    }
}

    