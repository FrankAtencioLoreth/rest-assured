package app.models;

public class GetUserResponse{
	private Data data;
	private Support support;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSupport(Support support){
		this.support = support;
	}

	public Support getSupport(){
		return support;
	}

	@Override
 	public String toString(){
		return 
			"GetUserResponse{" + 
			"data = '" + data + '\'' + 
			",support = '" + support + '\'' + 
			"}";
		}
}
