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
    
    private void fillLines(int midPoint, int spaceToPut){
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

    public void printDiamond3(){
        int midPoints = 1;
        int i;
        for(i=0;i<this.size && midPoints<=this.size;i++){
            String printLine = "";
            for(int j=0;j<this.size/2+1-i;j++){
                printLine+=" ";
            }
            for(int j=0;j<midPoints;j++){
                printLine+="*";
            }
            System.out.println(printLine);
            midPoints+=2;
            // System.out.println(i);
        }
        midPoints-=4;
        // System.out.println(i);
        for(;i>0 && midPoints>0;i--){
            String printLine = "";
            for(int j=0;j<Math.abs(this.size/2+3-i);j++){
                printLine+=" ";
            }
            for(int j=0;j<midPoints;j++){
                printLine+="*";
            }
            System.out.println(printLine);
            midPoints-=2;
        }
    }

    public void printDiamond4(){
        int starCounts = 1;
        int plusOrMinus = 2;
        String printLine;
        int spaceCount;
        for(int i=0;i<this.size;i++){
            printLine = "";
            spaceCount = Math.abs(this.size/2+1-starCounts/2);
            for(int j=0;j<spaceCount;j++) printLine+=" ";
            for(int j=0;j<starCounts;j++) printLine+="*";
            if(starCounts==this.size) plusOrMinus=-2;
            starCounts+=plusOrMinus;
            System.out.println(printLine);
        }
    }

    public static void main(String[] args){ 
        Diamond newOb = new Diamond(7);
        newOb.printDiamond4();
    }
}
