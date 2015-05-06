package one;

import java.util.Random;

public class Man {
	boolean strangeFeeling = true;
	boolean burnedOut = false;
	int dayOfDoWaitAndThink = 0;
	int beginRoutine = 10;
	boolean INSPIRATION = false;
	Random god = new Random();
	int mannaOfINSPIRATION = 1;

	private void liveWithInsipration() {

		while (!this.burnedOut && this.strangeFeeling) {
			this.dayOfDoWaitAndThink++;

			boolean myYesterdayINSPIRATION = INSPIRATION;
			this.whatIsWithMyINSPIRATION();
			if (myYesterdayINSPIRATION != INSPIRATION) {
				if (this.INSPIRATION) {
					this.mannaOfINSPIRATION = 2;
					sayHowItWas("I've got INSPIRATION!!!");
				} else {
					this.mannaOfINSPIRATION = 1;
					sayHowItWas("my inspiration gone...");
				}
			}
			sayHowItWas(liveDayOfWaitAndThink());
			if (this.dayOfDoWaitAndThink == this.beginRoutine) {
				strangeFeeling = false;
			}
			if (!this.DoYouFeelGood()) {
				burnedOut = true;
				sayHowItWas("I have burn out..");
			}
		}
	}

	private void whatIsWithMyINSPIRATION() {
		if (this.INSPIRATION == false) {
			if (god.nextDouble() < 0.15) {
				this.INSPIRATION = true;
			}
		} else {
			if (god.nextDouble() < 0.75) {
				this.INSPIRATION = false;
			}
		}
	}

	private boolean DoYouFeelGood() {
		if (god.nextDouble() < 0.95) {
			return true;
		}
		return false;
	}

	private int amountThoughtsInDay() {
		double godSign = god.nextGaussian();
		if (Math.abs(godSign) < 0.25/this.mannaOfINSPIRATION) {
			return 1;
		}
		if (Math.abs(godSign) < 0.5/this.mannaOfINSPIRATION) {
			return 2;
		}
		if (Math.abs(godSign) < 0.75/this.mannaOfINSPIRATION) {
			return 3;
		}
		if (Math.abs(godSign) < 1/this.mannaOfINSPIRATION) {
			return 4;
		}
		if (Math.abs(godSign) < 1.25/this.mannaOfINSPIRATION) {
			return 5;
		}
		if (Math.abs(godSign) < 1.5/this.mannaOfINSPIRATION) {
			return 6;
		}
		if (Math.abs(godSign) < 1.75/this.mannaOfINSPIRATION) {
			return 7;
		}
		if (Math.abs(godSign) < 2/this.mannaOfINSPIRATION) {
			return 8;
		}
		return 9;
	}

	private int amountStepsInThought() {
		double godSign = god.nextGaussian();
		if (Math.abs(godSign) < 0.25/this.mannaOfINSPIRATION) {
			return 9;
		}
		if (Math.abs(godSign) < 0.5/this.mannaOfINSPIRATION) {
			return 8;
		}
		if (Math.abs(godSign) < 0.75/this.mannaOfINSPIRATION) {
			return 7;
		}
		if (Math.abs(godSign) < 1/this.mannaOfINSPIRATION) {
			return 6;
		}
		if (Math.abs(godSign) < 1.25/this.mannaOfINSPIRATION) {
			return 5;
		}
		if (Math.abs(godSign) < 1.5/this.mannaOfINSPIRATION) {
			return 4;
		}
		if (Math.abs(godSign) < 1.75/this.mannaOfINSPIRATION) {
			return 3;
		}
		if (Math.abs(godSign) < 2/this.mannaOfINSPIRATION) {
			return 2;
		}
		return 1;
	}

	private String liveDayOfWaitAndThink() {
		int amountThoughtsInThisDay = this.amountThoughtsInDay();
		String thoughtsOfTheDay = "Day #" + this.dayOfDoWaitAndThink
				+ "\n_____________________________________ \n";
		for (int oneThought = 1; oneThought <= amountThoughtsInThisDay; oneThought++) {
			String resultOnethought = this.thinkOneThought();
			thoughtsOfTheDay += resultOnethought + "\n";
		}
		return thoughtsOfTheDay;
	}

	private String thinkOneThought() {
		int amountStepsInThisThought = this.amountStepsInThought();
		String howDidManThink = "";
		for (int oneStep = 1; oneStep <= amountStepsInThisThought; oneStep++) {
			if (this.doesItGoesGood() && oneStep < amountStepsInThisThought) {
				howDidManThink += "think.. ";
			} else {
				if (oneStep < amountStepsInThisThought) {
					howDidManThink += "fuck!";
					return howDidManThink;
				}
			}
			if (oneStep == amountStepsInThisThought) {
				howDidManThink += this.whatDidManFind();
			}
		}
		return howDidManThink;
	}

	private String whatDidManFind() {
		double godSign = god.nextGaussian();
		if (Math.abs(godSign) < 0.25/this.mannaOfINSPIRATION) {
			return "awful";
		}
		if (Math.abs(godSign) < 0.75/this.mannaOfINSPIRATION) {
			return "bad";
		}
		if (Math.abs(godSign) < 1.25/this.mannaOfINSPIRATION) {
			return "nothing special";
		}
		if (Math.abs(godSign) < 1.9/this.mannaOfINSPIRATION) {
			return "nice :)";
		}
		return "brilliant!!! :3";
	}

	private boolean doesItGoesGood() {
		if (god.nextDouble() < 0.9) {
			return true;
		}
		return false;
	}

	public static void sayHowItWas(Object o) {
		System.out.println(o);

	}

	public static void main(String[] args) {
		Man somebody = new Man();
		somebody.liveWithInsipration();
	}
}