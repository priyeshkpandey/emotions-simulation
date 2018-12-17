# Simulation Framework for Emotions

A simulation framework for Emotions based on OCC model (Ortony, A., Clore, G., & Collins, A. (1988). The Cognitive Structure of Emotions. Cambridge: Cambridge University Press. doi:10.1017/CBO9780511571299)

## Getting Started

To use the framework, simply add the below maven dependency to your project
```
<dependency>
  <groupId>com.github.priyeshkpandey</groupId>
  <artifactId>occ-model</artifactId>
  <version>1.0.0</version>
</dependency>
```

To understand how to use the framework, please refer the tests written for this framework under ```src/test/java```

### Prerequisites

```
Java 8
Maven
```

### Installing

Either clone the repo or include the dependency in your maven project

## Running the tests
Clone this repo.
After cloning, you can run the tests simply by running ```mvn clean install``` that will build and run the tests.

## Definitions of Variables

Values for all the variables are assumed falling in the same range.

| Variable Type | Description | Value Restrictions | Influenced Emotions |
|--------------|-------------|--------------------|---------------------|
| UNEXPECTEDNESS | How much is the action/event/property is unexpected | Within the same range as assumed by the user | All |
| SENSE_OF_REALITY | How much is the action/event/property is perceived to be real | Within the same range as assumed by the user | All |
| PROXIMITY | How much is the action/event/property is perceived to be psychologically near | Within the same range as assumed by the user | All |
| AROUSAL | How much is the action/event/property is causing arousal | Within the same range as assumed by the user | All |
| DESIRABILITY | How much is an event desirable | Within the same range as assumed by the user | Anger, Disappointment, Distress, Fear, Fears-Confirmed, Gloating, Gratitude, Happy-For, Hope, Joy, Relief, Remorse, Resentment, Satisfaction, Sorry-For |
| DESERVINGNESS | How much the agent deserved the event | Within the same range as assumed by the user | Gloating, Happy-For, Resentment, Sorry-For |
| LIKING | How much liking is there for the agent | Within the same range as assumed by the user | Gloating, Happy-For, Resentment, Sorry-For |
| DESIRABILITY_FOR_OTHER | How much is the event desirable for other | Within the same range as assumed by the user | Gloating, Happy-For, Resentment, Sorry-For |
| LIKELIHOOD | How much is the event likely to happen | Within the same range as assumed by the user | Disappointment, Fear, Fears-Confirmed, Hope, Relief, Satisfaction |
| REALIZATION | How much of the event is actually realized | Within the same range as assumed by the user | Disappointment, Fears-Confirmed, Relief, Satisfaction |
| EFFORT | How much effort is spent in realizing or avoiding the event | Within the same range as assumed by the user | Disappointment, Fears-Confirmed, Relief, Satisfaction |
| PRAISEWORTHINESS | How much praise worthy is agent's action | Within the same range as assumed by the user | Admiration, Anger, Gratification, Gratitude, Pride, Remorse, Reproach, Shame |
| STRENGTH_OF_COG_UNIT | How much unity/oneness is perceived with the agent | Within the same range as assumed by the user | Gratification, Pride, Remorse, Shame |
| EXPECTATION_DEVIATION | How much the action by agent deviated from normal expectations | Within the same range as assumed by the user | Admiration, Anger, Gratification, Gratitude, Pride, Remorse, Reproach, Shame |
| APPEALINGNESS | How much appealing is the object | Within the same range as assumed by the user | Love, Hate |
| FAMILIARITY | How much is the object familiar | Within the same range as assumed by the user | Love, Hate |


## Contributing

To contribute, please fork and make changes. Raise PR once you have committed your changes



## License

This project is licensed under the MIT license

