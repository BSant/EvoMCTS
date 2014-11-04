import core.ArcadeMachine;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 * Date: 04/10/13
 * Time: 16:29
 * This is a Java port from Tom Schaul's VGDL - https://github.com/schaul/py-vgdl
 */
public class Test
{

    public static void main(String[] args)
    {
        //Available controllers:
        String sampleRandomController = "controllers.sampleRandom.Agent";
        String sampleOneStepController = "controllers.sampleonesteplookahead.Agent";
        String sampleMCTSController = "controllers.sampleMCTS.Agent";
        String sampleGAController = "controllers.sampleGA.Agent";
        //String controller = "FastEvoMCTS.Agent";
        String controller = "TEVC_MCTS.Agent";
        String pathfinder = "controllers.pathfinder.Agent";

        //Available games:
        String gamesPath = "examples/gridphysics/";
        //String games[] = new String[]{"aliens", "boulderdash", "butterflies", "chase", "frogs",
        //       "missilecommand", "portals", "sokoban", "survivezombies", "zelda"};
        //String games[] = new String[]{"camelRace", "digdug", "firestorms", "infection", "firecaster",
        //                             "overload", "pacman", "seaquest", "whackamole", "eggomania"};
		
        String games[] = new String[]{"aliens", "boulderdash", "butterflies", "chase", "frogs",
                                      "missilecommand", "portals", "sokoban", "survivezombies", "zelda",
                                      "camelRace", "digdug", "firestorms", "infection", "firecaster",
                                      "overload", "pacman", "seaquest", "whackamole", "eggomania",
                                      "circle", "leftright"};



        //String games[] = new String[]{"aliens", "butterflies", "chase", "missilecommand", "survivezombies",
        //                              "camelRace", "infection", "seaquest", "whackamole", "eggomania"};

        //Other settings
        boolean visuals = true;
        String recordActionsFile = null; //where to record the actions executed. null if not to save.
        int seed = new Random().nextInt();
        System.out.println("Seed = " + seed);
        String wkDir = System.getProperty("user.dir");
        String filename = wkDir.substring(wkDir.lastIndexOf("\\")+1) + ".csv";
        //String filename = wkDir.substring(wkDir.lastIndexOf("\\")+1) + ".txt";
		
        //Game and level to play
        int gameIdx = 21;
        int levelIdx = 0; //level names from 0 to 4 (game_lvlN.txt).
        String game = gamesPath + games[gameIdx] + ".txt";
        String level1 = gamesPath + games[gameIdx] + "_lvl" + levelIdx +".txt";

        // 1. This starts a game, in a level, played by a human.
        // ArcadeMachine.playOneGame(game, level1, recordActionsFile, seed);

        // 2. This plays a game in a level by the controller.
        //ArcadeMachine.runOneGame(game, level1, visuals, sampleMCTSController, recordActionsFile, seed);
        //ArcadeMachine.runOneGame(game, level1, visuals, controller, recordActionsFile, seed);

        // 3. This replays a game from an action file previously recorded
        //String readActionsFile = "actionsFile_aliens_lvl0.txt";  //This example is for
        //ArcadeMachine.replayGame(game, level1, visuals, readActionsFile);

        // 4. This plays a single game, in N levels, M times :
        //String level2 = gamesPath + games[gameIdx] + "_lvl" + 1 +".txt";//

//        //int M = 100;
//        int M = 1000;   //number of repeats (only for statistical accuracy)
//        int RLmin = 8;     //rollout lenghts to be tested from RLmin to RLmax, where RLmin >= 1
//        int RLmax = 30;
//        boolean isFixedTest = false;
//        ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, controller, isFixedTest, seed, filename);
//        //ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, sampleMCTSController, isFixedTest, seed, filename);


       RightLeft_2014_11_04(gamesPath, games, controller, sampleMCTSController, seed, filename);

       /* int M = 100;
        boolean isFixedTest = true;
        RLmin = 1;
        RLmax = 15;
        for(int i = 10; i > 0; --i)
        {
            filename = wkDir.substring(wkDir.lastIndexOf("\\")+1)  + i + ".txt";
            level1 = gamesPath + games[gameIdx] + "_lvl" + i +".txt";
            System.out.println("filename: " + filename + ", levelFile: " + level1);
            //ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, controller, isFixedTest, seed, filename);
            ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, sampleMCTSController, isFixedTest, seed, filename);
        }*/


        //5. This plays N games, in the first L levels, M times each. Actions to file optional (set saveActions to true).
        /*int N = 30, L = 1, M = 1;
        boolean saveActions = false;
        String[] levels = new String[L];
        String[] actionFiles = new String[L*M];
        for(int i = 0; i < N; ++i)
        {
            int actionIdx = 0;
            game = gamesPath + games[i] + ".txt";
            for(int j = 0; j < L; ++j){
                levels[j] = gamesPath + games[i] + "_lvl" + j +".txt";
                if(saveActions) for(int k = 0; k < M; ++k)
                    actionFiles[actionIdx++] = "actions_game_" + i + "_level_" + j + "_" + k + ".txt";
            }
            ArcadeMachine.runGames(game, levels, M, controller, saveActions? actionFiles:null, seed);
        }
                     */
    }

    public static void RightLeft_2014_11_04(String gamesPath, String[] games, String controller, String sampleMCTSController, int seed, String filename){

        int gameIdx = 21;
        int levelIdx = 0; //level names from 0 to 4 (game_lvlN.txt).
        String game = gamesPath + games[gameIdx] + ".txt";
        String level1 = gamesPath + games[gameIdx] + "_lvl" + levelIdx +".txt";

        int M = 10000;   //number of repeats (only for statistical accuracy)
        int RLmin = 1;     //rollout lenghts to be tested from RLmin to RLmax, where RLmin >= 1
        int RLmax = 8;

        //int M = 1000;   //number of repeats (only for statistical accuracy)
        //int RLmin = 9;     //rollout lenghts to be tested from RLmin to RLmax, where RLmin >= 1
        //int RLmax = 30;

        boolean isFixedTest = false;
        ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, controller, isFixedTest, seed, filename);
        //ArcadeMachine.runGamesN(game, level1, M, RLmin, RLmax, sampleMCTSController, isFixedTest, seed, filename);
    }
}
