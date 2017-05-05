package net.lawyee.mobilewidget;

/**
 * 
 * @author wuzhu
 *
 */
public class ItemInfo
{
	/**
	 * ��Ŀͼ��
	 */
	public int resid=0;
	
	/**
	 * ��Ŀѡ�к��ͼ��
	 */
	public int selresid=0;
	
	/**
	 * �Ƿ�ѡ��
	 */
	public Boolean isSel=false;
	
	/**
	 * ���
	 */
	public String text;
	
	@Override
	public String toString()
	{
		return text;
	}
}
