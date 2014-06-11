package todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class CheckUtil {
	private ArrayList<String>_errs = null;
	
	// (0)�R���X�g���N�^
	public CheckUtil(){
		// �v���C�x�[�g�ϐ�_errs��������
		this._errs =_errs;
	}
	
	// (1)�K�{����
	public void requiredCheck(String value,String name){
		if(value == null || value.trim().isEmpty()){
			this._errs.add(name + "�K�{���͂ł��B");
		}
	}
	
	// (2)�����񒷌���(max�����ȓ��ł��邩)
	public void lengthCheck(String value,int max,String name){
		if(value != null && !value.trim().isEmpty()){
			if(value.length() > max){
				this._errs.add(name + "��" + max + "�����ȉ��œ��͂��Ă��������B");
			}
		}
	}
	
	// (3)�����񒷌���(max�����ȓ��ł��邩)
		public void numberTypeCheck(String value,String name){
			if(value != null && !value.trim().isEmpty()){
				try{
					Integer.parseInt(value);
				}catch(NumberFormatException e){
					this._errs.add(name + "�͐��l�œ��͂��Ă��������B");
				}
			}
		}
	
	// (4)���l�͈͌���(min~max�͈̔͂ɂ��邩)
	public void rangeCheck(String value,int max,int min,String name){
		if(value != null && !value.trim().isEmpty()){
			int val = 0;
			try{
				val = Integer.parseInt(value);
			}catch(NumberFormatException e){
				this._errs.add(name + "�͐��l�œ��͂��Ă��������B");
			}
			if(val < min || val > max){
				this._errs.add(name + "��" + min + "�ȏ� �A����" + max + "�ȉ��œ��͂��Ă��������B");
			}
		}
	}
	
	// (5)���t�^����
	public void dateTypeCheck(String value,String name){
		if(value != null && !value.equals("")){
			// [YYYY-MM-DD]�̌`���ł��邩���`�F�b�N
			if(value.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$")){
				// �N�����𕪉�����Calendar�I�u�W�F�N�g�𐶐�
				StringTokenizer token = new StringTokenizer(value,"-"); 
				int year = Integer.parseInt((String)token.nextToken());
				int month = Integer.parseInt((String)token.nextToken());
				int day = Integer.parseInt((String)token.nextToken());
				Calendar cal = Calendar.getInstance();
				cal.set(year,month - 1,day);
				
				if(cal.get(Calendar.YEAR) != year || cal.get(Calendar.MONTH) != month - 1 || cal.get(Calendar.DATE) != day){
					this._errs.add(name + "�͐��������t�œ��͂��Ă��������B");
				}else{
					this._errs.add(_errs + "�͓��t�`���œ��͂��Ă��������B");
				}
			}
		}
	}
	
	// (6)���K�\���p�^�[������
	public void regExCheck
}