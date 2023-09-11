package Lab1;

public class Diamond {
    int size;

    public Diamond(int size){
        this.size = size;
    }

    // public void printDiamond(){
    //     int k = 1;
    //     while(k<=this.size){
    //         String lineToPrint = "";
    //         int toCount = 0;
    //         while(toCount<k){
    //             lineToPrint+="*";
    //             toCount+=1;
    //         }
    //         System.out.println(lineToPrint);
    //         k+=2;
    //     }
    // }
    public void printDiamond(){
        int midPoint = 1;
        int k=size/2+1;
        int spaces;
        int stars = 0;
        String lineToPrint;
        while(midPoint<=this.size){
            lineToPrint = "";
            spaces = 0;
            while(spaces<k){
                lineToPrint += " ";
                spaces+=1;
            }
            stars = 0;
            while(stars<midPoint){
                lineToPrint += "*";
                stars+=1;
            }
            while(spaces<k){
                lineToPrint += " ";
                spaces+=1;
            }
            System.out.println(lineToPrint);
            k-=1;
            midPoint+=2;
        }
        midPoint-=4;
        k+=2;
        // System.out.println(k);
        while(midPoint>=0){
            lineToPrint = "";
            spaces = 0;
            while(spaces<k) {
                lineToPrint += " ";
                spaces+=1;
            }
            stars = 0;
            while(stars<midPoint){
                lineToPrint += "*";
                stars+=1;
            }
            while(spaces<k){
                lineToPrint += " ";
                spaces+=1;
            }
            System.out.println(lineToPrint);
            k+=1;
            midPoint-=2;
        }
    }
    
    public void fillLines(int midPoint, int spaceToPut){
        String toPrint = "";
        for(int i=0;i<spaceToPut;i++){
            toPrint+=" ";
        }
        for(int i=0;i<midPoint;i++){
            toPrint+="*";
        }
        System.out.println(toPrint);
    }

    public void printDiamond2(){
        int midPoint=1;
        int spaceToPut = size/2;
        while(midPoint<this.size){
            fillLines(midPoint, spaceToPut);
            midPoint+=2;
            spaceToPut-=1;
        }
        while(midPoint>=1){
            fillLines(midPoint, spaceToPut);
            midPoint-=2;
            spaceToPut+=1;
        }
    }

    public static void main(String[] args){
        Diamond newOb = new Diamond(7);
        newOb.printDiamond2();
    }
}
