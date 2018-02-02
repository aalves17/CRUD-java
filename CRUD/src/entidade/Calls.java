package entidade;

public class Calls {
	private int id;
	private String call_label;
	private String call_critic;
	private String call_create_date;
	private String call_expire_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCall_label() {
		return call_label;
	}
	public void setCall_label(String call_label) {
		this.call_label = call_label;
	}
	public String getCall_create_date() {
		return call_create_date;
	}
	public void setCall_create_date(String call_create_date) {
		this.call_create_date = call_create_date;
	}
	public String getCall_expire_date() {
		return call_expire_date;
	}
	public void setCall_expire_date(String call_expire_date) {
		this.call_expire_date = call_expire_date;
	}
	public String getCall_critic() {
		return call_critic;
	}
	public void setCall_critic(String call_critic) {
		this.call_critic = call_critic;
	}	
	
	public Calls(int id, String call_label, String call_create_date, String call_expire_date) {
		super();
		this.id = id;
		this.call_label = call_label;
		this.call_create_date = call_create_date;
		this.call_expire_date = call_expire_date;
	}
	
	public Calls() {
		super();
	}
	
}