package app.models;

public class ResponseUser{
	private String createdAt;
	private String name;
	private String id;
	private String job;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	@Override
	public String toString() {
		return "id: "+this.id+"\nname: "+this.name+"\njob: "+this.job+"\ncreatedAt: "+this.createdAt;
	}
}
