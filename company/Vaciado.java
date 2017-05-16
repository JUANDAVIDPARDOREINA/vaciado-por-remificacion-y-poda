package com.company;

/**
 * Created by JUANDAVID on 15/05/17.
 */
public class Vaciado {

    String anteriores = "";
    int minino = -1, a,b,c,d,e,f;

    // debe llegar siempre x como vaso de mayor capacidad para evitar comparaciones.
    public int comparaVaso(int x, int z, int c, int xTemp, int zTemp, int pasos){

        int auxZ = 0, auxX = 0;

        // si los vasos estan vacio siempre a de llenarse el vaso de mas capacidad a no ser que
        // el vaso de menos capacidad sea igual a c (c es la cantiodad deseada).
        if (x == c || z == c){
            return 1;
        }else if(x < c){
            return -1;
        }else if( (x%2 == 0) && (z%2 == 0) && (c%2 == 1) ){
            return -1;
        }else if(xTemp == c || zTemp == c){// si el vaciado de uno de los vasos en otro es igual a c debe salirse
          getMinino(pasos);
        }else{
            // Si vienen vacios se debe llenar solo uno de los dos
            // de otro modo podria vaciar uno de los dos
            if (xTemp  == 0 && zTemp == 0){
                a = comparaVaso(x,z,c,0,z, 0);
            }else{
                // vaciamos x
                if(this.pasoHecho("-"+0+"."+zTemp+"-")){
                    a = comparaVaso(x,z,c,0,zTemp, pasos+1);
                }
            }

            if (xTemp  == 0 && zTemp == 0){
                b = comparaVaso(x,z,c,x,0, 0);
            }else{
                // vaciamos z
                if(this.pasoHecho("-"+xTemp+"."+0+"-")){
                    b = comparaVaso(x,z,c,xTemp,0, pasos+1);
                }
            }

            // llenamos x
            if(this.pasoHecho("-"+x+"."+zTemp+"-")){
                c = comparaVaso(x,z,c,x,zTemp, pasos+1);
            }
            // llenamos z
            if(this.pasoHecho("-"+xTemp+"."+z+"-")){
                d =comparaVaso(x,z,c,xTemp,z, pasos+1);
            }

            if (xTemp > z && zTemp == 0) {auxX = xTemp - z; auxZ = z;}
            else if (xTemp >  z && zTemp != 0) {auxX = xTemp - (z - zTemp); auxZ = z; }
            else if (xTemp <= z && zTemp == 0) {auxX = 0; auxZ = xTemp; }
            else if (xTemp <= z && zTemp != 0) {auxX = xTemp - (z - zTemp); auxZ = z - zTemp; }
            else if ((zTemp + xTemp) <= x && zTemp == 0) {auxX = (zTemp + xTemp); auxZ = 0;}
            else if (xTemp < x && (xTemp + zTemp) > x) {auxX = x; auxZ = zTemp - ( x - xTemp); }
            else if (xTemp == 0) {auxX = zTemp; auxZ = 0; }

            if(this.pasoHecho("-"+auxX+"."+auxZ+"-")){
                e = comparaVaso(x,z,c,auxX,auxZ,pasos+1);
            }

        }

        return this.minino-1;
        // aqui comprobaremos que no se halla hecho ya los pasos para evitar repetirlos.
    }



    public  boolean pasoHecho (String buscar){

        // si el sttring no extia el paso no se a dado por tanto se puede seguir buscando
        if (this.anteriores.indexOf(buscar) == -1){
            this.anteriores += buscar;
            return true;
        }else{
            return false;
        }

    }

    public int getMinino (int n){
        if(this.minino > n || this.minino == -1){
            this.minino = n;
        }
        return this.minino;

    }

}
