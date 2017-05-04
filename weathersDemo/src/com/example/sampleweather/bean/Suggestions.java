package com.example.sampleweather.bean;

public class Suggestions {
	public Air air;

	public Comf comf;

	public Cw cw;

	public Drsg drsg;

	public Flu flu;

	public Sport sport;

	public Trav trav;

	public Uv uv;

	public class Air{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Air [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Comf{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Comf [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Cw{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Cw [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Drsg{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Drsg [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Flu{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Flu [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Sport{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Sport [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Trav{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Trav [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	public class Uv{
		public String brf;
		public String txt;
		@Override
		public String toString() {
			return "Uv [brf=" + brf + ", txt=" + txt + "]";
		}
		
	}
	@Override
	public String toString() {
		return "Suggestions [air=" + air + ", comf=" + comf + ", cw=" + cw
				+ ", drsg=" + drsg + ", flu=" + flu + ", sport=" + sport
				+ ", trav=" + trav + ", uv=" + uv + "]";
	}
	

}
