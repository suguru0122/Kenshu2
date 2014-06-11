package todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class CheckUtil {
	private ArrayList<String>_errs = null;
	
	// (0)コンストラクタ
	public CheckUtil(){
		// プライベート変数_errsを初期化
		this._errs =_errs;
	}
	
	// (1)必須検証
	public void requiredCheck(String value,String name){
		if(value == null || value.trim().isEmpty()){
			this._errs.add(name + "必須入力です。");
		}
	}
	
	// (2)文字列長検証(max文字以内であるか)
	public void lengthCheck(String value,int max,String name){
		if(value != null && !value.trim().isEmpty()){
			if(value.length() > max){
				this._errs.add(name + "は" + max + "文字以下で入力してください。");
			}
		}
	}
	
	// (3)文字列長検証(max文字以内であるか)
		public void numberTypeCheck(String value,String name){
			if(value != null && !value.trim().isEmpty()){
				try{
					Integer.parseInt(value);
				}catch(NumberFormatException e){
					this._errs.add(name + "は数値で入力してください。");
				}
			}
		}
	
	// (4)数値範囲検証(min~maxの範囲にあるか)
	public void rangeCheck(String value,int max,int min,String name){
		if(value != null && !value.trim().isEmpty()){
			int val = 0;
			try{
				val = Integer.parseInt(value);
			}catch(NumberFormatException e){
				this._errs.add(name + "は数値で入力してください。");
			}
			if(val < min || val > max){
				this._errs.add(name + "は" + min + "以上 、かつ" + max + "以下で入力してください。");
			}
		}
	}
	
	// (5)日付型検証
	public void dateTypeCheck(String value,String name){
		if(value != null && !value.equals("")){
			// [YYYY-MM-DD]の形式であるかをチェック
			if(value.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$")){
				// 年月日を分解してCalendarオブジェクトを生成
				StringTokenizer token = new StringTokenizer(value,"-"); 
				int year = Integer.parseInt((String)token.nextToken());
				int month = Integer.parseInt((String)token.nextToken());
				int day = Integer.parseInt((String)token.nextToken());
				Calendar cal = Calendar.getInstance();
				cal.set(year,month - 1,day);
				
				if(cal.get(Calendar.YEAR) != year || cal.get(Calendar.MONTH) != month - 1 || cal.get(Calendar.DATE) != day){
					this._errs.add(name + "は正しい日付で入力してください。");
				}else{
					this._errs.add(_errs + "は日付形式で入力してください。");
				}
			}
		}
	}
	
	// (6)正規表現パターン検証
	public void regExCheck
}