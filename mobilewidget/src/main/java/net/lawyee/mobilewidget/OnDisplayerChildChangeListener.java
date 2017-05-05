package net.lawyee.mobilewidget;

/**
 * �Ӽ�View������ӿ�
 * @author wuzhu
 *
 */
public interface OnDisplayerChildChangeListener {

	/**
	 * ��ʾ�Ӽ�View�䶯�ص�����
	 * @param oldWhichChild �䶯ǰ���Ӽ�����
	 * @param whichChild �䶯����Ӽ�����
	 */
	public void onChanged(int oldWhichChild,int whichChild);
}
