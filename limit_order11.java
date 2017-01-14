import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
//新しいTick Dataでの指値注文の注文量と間隔を求めるプログラム　約定と約定の間にある指値を数えるプログラム
public class limit_order11{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("filelist.txt"));//読み取りたいファイル名の記入
        String txtFileName;

        while((txtFileName = br.readLine()) != null) {

        	String Index;//asdas
        	String number[] = new String[5000000];
        	String a;
        	int n = 0;
        	int number1 = 0;
            int count = 0;
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int count5 = 0;
            int count6 = 0;
            int count7 = 0;
            int count8 = 0;
            int count9 = 0;
            int count10 = 0;
            int count11 = 0;
            int count12 = 0;
            int count13 = 0;
            int count14 = 0;
            int count_dif[] = new int [10000];
            int count_dif_number = 0;
            int count_hiruma1 = 0;
            String day = null;//日付
            String transaction = null;//Quote or Trade
            String time = null;//時間
            double time1 = 0.0;//時
            double time2 = 0.0;//分
            double time3 = 0.0;//秒
            double time_total1 = 0.0;//時間を秒で表示(time1,time2,time3を使いながら)
            double time_total2 = 0.0;
            double time_dif = 0.0;
            String day1 = null;
            int day_number = 0;
            String bid_volume;//最良買気配の累積枚数
            int bid_volume1 = 0;//最良買気配の累積枚数
            int bid_volume2 = 0;
            int bid_volume_dif = 0;//前の買いの指値注文からの増減
            String ask_volume;//最良売気配の累積枚数
            int ask_volume1 = 0;//最良売気配の累積枚数
            int ask_volume2 = 0;
            int ask_volume_dif = 0;//前の売りの指値注文からの増減
            String trade_price;
            int trade_price1 = 0;
            String trade_volume;
            int trade_volume1 = 0;
            String bid;//最良買気配の値段
            int bid1[] = new int[800000];//最良買気配の値段
            String ask;//最良売気配の値段
            int ask1[] = new int[800000];//最良売気配の値段
            int price_dif = 0;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int bid_price_first = 0;
            int ask_price_first = 0;
            int bid_volume_first = 0;
            int ask_volume_first = 0;
            String time_first = null;


            //for文の部分
            double time_interval = 5.0;//5秒間隔
            double time_limit = 6000.0;//6000秒まで行う
            int for_array = 1200;//countで用いる配列の数(count[]の箱の数)

            int bid_volume_dif_total = 0;
            int ask_volume_dif_total = 0;
            int count_pw = 0;
            int volume_dif = 0;



        	FileReader fr = new FileReader("/volumes/HDJA-UT/日経平均先物/日経平均先物データ/" + txtFileName);
            BufferedReader brtxt = new BufferedReader(fr);
            String line ="";

            String[] filename = txtFileName.split(".");

         	File file = new File(filename[0] + "_limit_order.txt");
         	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            while ((line = brtxt.readLine()) != null) {

            	Index = line;
            	i2 = Index.length();
            	//if(i2>13){
            	day = line.substring(6,14);//年月日
            	day_number = Integer.parseInt(day);//年月日
            	time = line.substring(15,27);//時間
            	transaction = line.substring(28,33);//Quote or Trade
        		String c1 = Index.substring(15,17);//時
        		String c2 = Index.substring(18,20);//分
        		String c3 = Index.substring(21,27);//秒
        		time1 = Double.parseDouble(c1);//時
        		time2 = Double.parseDouble(c2);//分
        		time3 = Double.parseDouble(c3);//秒
        		time_total1 = time1*3600 + time2*60 + time3;//時間を秒で表示
            	
            	//}
            	if(!(day.equals(day1))){
            		if(count14 != 0){
                		//for(int i = 0;i<for_array;i++){
                			//if(count_dif[i]==0){
                        		//pw.println();//秒後の板
                        		//break;
                			//}
                		//}
                	}
            		count_hiruma1 = 0;
            		count13 = 0;
            		count14 = 0;
            		for(int i = 0;i<for_array;i++){
            			count_dif[i]=0;
            		}
            		number1 = 0;
                	bid1[0]=0;
                	ask1[0]=0;
                	bid1[1]=0;
                	ask1[1]=0;
            	}

            	if(time_total1 > 45000.0 && count_hiruma1 == 0 && day_number < 20110214){
            		//System.out.println(day + " " + time);
            		if(count14 != 0){
                		//for(int i = 0;i<for_array;i++){
                			//if(count_dif[i]==0){
            					//pw.println();//秒後の板
                        		//break;
                			//}
                		//}
                	}
            		count13 = 0;
            		count14 = 0;
            		for(int i = 0;i<for_array;i++){
            			count_dif[i]=0;
            		}
            		number1 = 0;
            		bid_volume1 = 0;
            		ask_volume1 = 0;
                	bid1[0]=0;
                	ask1[0]=0;
                	bid1[1]=0;
                	ask1[1]=0;
            		count_hiruma1++;
            	}


                for(i1=0;i1<i2;i1++){
                    a = Index.substring(i1,i1+1);
                    if(count == 5 && !(a.equals(",")) && count1 == 0){
                    	i7 = i1;
                    	count1++;
                    }
                    if(count == 6 && count1 != 0 && count2 == 0){
                    	i8 = i1-1;
                    	trade_price = Index.substring(i7,i8);//Tradeの約定価格
                    	trade_price1 =Integer.parseInt(trade_price);
                    	count2++;
                    }
                    if(count == 6 && !(a.equals(",")) && count3 == 0){
                    	i9 = i1;
                    	count3++;
                    }
                    if(count == 7 && count3 != 0 && count4 == 0){
                    	i10 = i1-1;
                    	trade_volume = Index.substring(i9,i10);//Tradeの出来高
                    	trade_volume1 =Integer.parseInt(trade_volume);
                    	count4++;
                    }

                    if(count == 8 && !(a.equals(",")) && count5 == 0){
                        i3 = i1;
                        count5++;
                    }

                    if(count == 9 && count5 != 0 && count6 == 0){
                        count6++;
                        i4 = i1-1;
                        bid=Index.substring(i3,i4);//最良買気配の値段
                        if(bid.equals(",")){
                            bid1[number1] = 0;
                        }
                        else{
                            bid1[number1] = Integer.parseInt(bid);
                        }
                    }
                    if(count == 9 && !(a.equals(",")) && count7 == 0){
                    	count7++;
                    	i5 = i1;
                    }
                    if(count == 10 && count7 != 0 && count8 == 0){
                    	i6 = i1-1;
                    	bid_volume = Index.substring(i5,i6);//最良売気配の累積枚数
                    	bid_volume1 =Integer.parseInt(bid_volume);
                    	count8++;
                    }
                    if(count == 10 && !(a.equals(",")) && count9 == 0){
                        i3 = i1;
                        count9++;
                    }
                    if(count == 11 && count9 != 0 && count10 == 0){
                        count10++;
                        i4 = i1-1;
                        ask=Index.substring(i3,i4);//最良売気配の値段
                        if(ask.equals(",")){
                            ask1[number1] = 0;
                        }
                        else{
                            ask1[number1] = Integer.parseInt(ask);
                        }
                    }
                    if(count == 11 && !(a.equals(",")) && count11 == 0){
                    	i5 = i1;
                    	count11++;
                    }
                    if(count == 12 && count11 != 0 && count12 == 0){
                    	i6 = i1-1;
                    	ask_volume = Index.substring(i5,i6);//最良売気配の累積枚数
                    	ask_volume1 =Integer.parseInt(ask_volume);
                    	count12++;
                    }
                    if(a.equals(",")){
                        count++;
                    }
                }
                if(bid1[1]>ask1[1] && bid1[1] != 0 && ask1[1] != 0){//意味の分からないことが起きていないか確認
                													//
                	System.out.println(day + " " + time + " " + bid1[1] + " " + ask1[1]);
                }

                if(bid1[0] != ask1[0] && count13 == 0){//寄り付きが終了したときの初期値の設定
                	count13++;
                	number1++;
                	day1 = day;
                	bid_volume2 = bid_volume1;
                	ask_volume2 = ask_volume1;
                	time_total2 = time_total1;
                }
                /*else if((bid1[0] != bid1[1] || ask1[0] != ask1[1]) && count13 != 0 && bid1[1] != 0 && ask1[1] != 0 && bid1[1] != ask1[1]){//板が移動したことを認知するプログラム
                	time_first = time;
                	bid_price_first = bid1[1];
                	ask_price_first = ask1[1];
                	bid_volume_first = bid_volume1;
                	ask_volume_first = ask_volume1;
                	bid_volume2 = bid_volume1;
                	ask_volume2 = ask_volume1;
                	time_total2 = time_total1;
                	count14++;
                	if(count14 > 1 && (bid1[0] == bid1[1] && ask1[0] != ask1[1]) || (bid1[0] != bid1[1] && ask1[0] == ask1[1])){
                		count_pw++;
                	}
                	bid1[0] = bid1[1];
                	ask1[0] = ask1[1];
                }*/
                else if(count13 != 0){//共通
                	time_dif = time_total1 - time_total2;

                		if(bid_volume1 != 0 && ask_volume1 == 0){//実は意味ない(古いデータのときには必要だった)
                    		bid_volume_dif = bid_volume1 - bid_volume2;
                    		bid_volume2 += bid_volume_dif;

                    	}
                		else if(bid_volume1 == 0 && ask_volume1 != 0){//実は意味ない(古いデータのときには必要だった)
                    		ask_volume_dif = ask_volume1 - ask_volume2;
                    		ask_volume2 += ask_volume_dif;

                    	}
                		else if(bid_volume1 != 0 && ask_volume1 != 0 && !(transaction.equals("Trade"))){
                    		bid_volume_dif = bid_volume1 - bid_volume2;
                    		if(bid_volume_dif <= 0){
                    			bid_volume2 = bid_volume1;
                    		}
                    		else{
                    			bid_volume2 += bid_volume_dif;
                    			bid_volume_dif_total +=bid_volume_dif;
                    		}
                    		ask_volume_dif = ask_volume1 - ask_volume2;
                    		if(ask_volume_dif <= 0){
                    			ask_volume2 = ask_volume1;
                    		}
                    		else{
                    			ask_volume2 += ask_volume_dif;
                    			ask_volume_dif_total += ask_volume_dif;
                    		}
                    		volume_dif = bid_volume_dif + ask_volume_dif;//なぜ足しているのか謎
                    		/*if(volume_dif > 0){
                    			pw.println(day + " " + time + " " + volume_dif);//指値注文の書き込み
                    		}*/
                    		if(bid_volume_dif > 0){
                    			pw.println(day + " " + time + " " + volume_dif + " bid");//指値買注文の書き込み
                    		}
                    		else if(ask_volume_dif > 0){
                    			pw.println(day + " " + time + " " + volume_dif + " ask");//指値売注文の書き込み
                    		}

                    	}
                		else if(transaction.equals("Trade")){
                			BigDecimal x4 = new BigDecimal(time_dif);
                    		double time_dif_x = x4.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    		if(count_pw == 0 && (bid_volume_dif_total != 0 || ask_volume_dif_total != 0)){
                    			//pw.println(day + " " + time + " " + bid1[0] + " " + ask1[0] + " " + time_dif_x
                    				//	+ " " + bid_volume_dif_total + " " + ask_volume_dif_total);
                    		}
                			time_total2 = time_total1;
                			count_pw = 0;
                			count14 = 0;
                			bid_volume_dif_total = 0;
                			ask_volume_dif_total = 0;
                    		if(trade_price1 == bid1[0]){
                    			bid_volume2 -= trade_volume1;
                    		}
                    		else if(trade_price1 == ask1[0]){
                    			ask_volume2 -= trade_volume1;
                    		}
                		}
                }


                count1=0;
                count2=0;
                count3=0;
                count4=0;
                count5=0;
                count6=0;
                count7=0;
                count8=0;
                count9=0;
                count10=0;
                count11=0;
                count12=0;
                count=0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
                i10 = 0;
                n++;


            }
            if(count14 != 0){
        		//for(int i = 0;i<for_array;i++){
        			//if(count_dif[i]==0){
            			//pw.println();//秒後の板
                		//break;
        			//}
        		//}
        	}

            brtxt.close();
            fr.close();
            pw.close();

        	// txtファイル名を一行ずつロードする



        }

        br.close();
    }
}

