package net.lawyee.mobilelib.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import net.lawyee.mobilelib.Constants;
import net.lawyee.mobilelib.utils.JavaLangUtil;
import net.lawyee.mobilelib.utils.SerializeUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.utils.TimeUtil;

import android.content.Context;

/**
 * VO基类
 * 
 * @author wuzhu
 * @date 2013-4-21 下午5:06:44
 * @version $id$
 */
public class BaseVO implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3185317889045447937L;

	/**
	 * ID 用于标识，所有子类如果自建id时，需要要同时给此赋值
	 */
	protected long id = 0l;

	/**
	 * 国家法官学院需要
	 */
	protected String infoId = "";
	/**
	 * VO数据创建时间
	 */
	protected Date voCreateDate = new Date();

	public Date getVoCreateDate() {
		return voCreateDate;
	}

	public void setVoCreateDate(Date voCreateDate) {
		this.voCreateDate = voCreateDate;
	}

	public void setVoCreateDate(String voCreateDate) {
		this.voCreateDate = TimeUtil.strToDate(voCreateDate, new Date());
		;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = JavaLangUtil.StrToLong(id, 0l);
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	/**
	 * 重载，增加判断：类一样且id一致时返回true
	 */
	@Override
	public boolean equals(Object o) {
		if (super.equals(o))
			return true;
		if (o == null)
			return false;
		if (o instanceof BaseVO&&((BaseVO) o).getId() == 0 ) {

			return this.getClass().getSimpleName()
					.equals(o.getClass().getSimpleName())
					&& this.infoId.equals(((BaseVO) o).infoId);

		}
		return this.getClass().getSimpleName()
				.equals(o.getClass().getSimpleName())
				&& this.id == ((BaseVO) o).id;

	}

	@Override
	public String toString() {
		return SerializeUtil.objectSerialzeTOString(this);
	}

	/**
	 * VO存储文件名(注意，这个是读取单个对象的名称，读取列表请使用dataListFileName)
	 * 
	 * @param uid
	 * @return
	 */
	public static String dataFileName(long uid) {
		return Constants.getDataStoreDir() + "d" + Math.abs(uid) + ".	dat";
}

	/**
	 * VO存储文件名
	 * 
	 * @param uid
	 * @param suf
	 * @return
	 */
	public static String dataFileName(long uid, String suf) {
		if (StringUtil.isEmpty(suf))
			return dataFileName(uid);
		return Constants.getDataStoreDir() + "d" + Math.abs(uid) + "_" + suf
				+ ".dat";
	}

	/**
	 * VO列表存储文件名
	 * 
	 * @param uid
	 * @return
	 */
	public static String dataListFileName(long uid) {
		return Constants.getDataStoreDir() + "dl" + Math.abs(uid) + ".dat";
	}

	/**
	 * VO列表存储文件名
	 * 
	 * @param uid
	 * @param suf
	 * @return
	 */
	public static String dataListFileName(long uid, String suf) {
		if (StringUtil.isEmpty(suf))
			return dataListFileName(uid);
		return Constants.getDataStoreDir() + "dl" + Math.abs(uid) + "_" + suf
				+ ".dat";
	}

	/**
	 * 返回VO类创建时间和给定的时间之间的间隔，单位ms
	 * 
	 * @param date
	 * @return
	 */
	public long getDateInterval(Date date) {
		return TimeUtil.interval(date, voCreateDate);
	}

	public static boolean saveVO(BaseVO vo, String filename) {
		return SerializeUtil.save(filename, vo, true);
	}

	public static BaseVO loadVO(String filename) {
		Object o = SerializeUtil.load(filename);
		if (o != null && o instanceof BaseVO)
			return (BaseVO) o;
		return null;
	}

	public static boolean saveVOList(ArrayList<?> vos, String filename) {
		return SerializeUtil.saveArraylistToFile(vos, filename, true);
	}

	public static ArrayList<?> loadVOList(String filename) {
		return SerializeUtil.loadArraylistFromFile(filename);
	}

	/**
	 * 是否为时间内有效的数据
	 * 
	 * @param efftime
	 *            和当前时间差多少内为有效数据
	 * @return
	 */
	public boolean isEffectiveTimeData(long efftime) {
		return TimeUtil.interval(new Date(), getVoCreateDate()) <= efftime;
	}

	/**
	 * 返回详情HTML标题
	 * 
	 * @return
	 */
	public String getHtmlTitle() {
		return "";
	}

	/**
	 * 返回详情HTML子标题
	 * 
	 * @return
	 */
	public String getHtmlSubTitle() {
		return "";
	}

	/**
	 * 生成分享信息内容部分
	 */
	public String generateShareText(Context c) {
		return "";
	}

	@SuppressWarnings("rawtypes")
	public static String getLocalDataList(ArrayList datalist) {
		if (datalist == null || datalist.isEmpty()) {
			return StringUtil.STR_EMPTY;
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < datalist.size(); i++) {
			Object o = datalist.get(i);
			if (o != null && o instanceof BaseVO) {
				result.append("'" + ((BaseVO) o).getId() + "'");
			}
			if (i != datalist.size() - 1) {
				result.append(",");
			}
		}
		return result.toString();
	}
}
