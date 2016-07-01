package jogo_call;

import java.awt.Component;
import javax.swing.*;
import javax.swing.JOptionPane;

public class TwentyQuestions {

    public static void main(String[] args) {
        BTNode<String> root;
        instruct();
        root = beginningTree();
        do {
            play(root);
        } while (query("Vamos jogar novamente?"));

        JOptionPane.showMessageDialog(null, "Obrigado! Criado por Fábio Neves!");
        root.print(1);
        System.exit(0);
    }

    public static void instruct() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Pense em um animal.");
    }

    public static void play(BTNode<String> current) {
        while (!current.isLeaf()) {
            if (query(current.getData())) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (!query("Eu acho que é " + current.getData() + ".Estou certo?")) {
            learn(current);
        } else {
            JOptionPane.showMessageDialog(null, "Sabia que eu estava certo!");
        }
    }

    public static BTNode<String> beginningTree() {
        BTNode<String> root;
        BTNode<String> child;

        final String ROOT_QUESTION = "O animal vive na água?";
        final String LEFT_QUESTION = "O animal é maior que um gato?";
        final String RIGHT_QUESTION = "O animal é um roedor?";
        final String ANIMAL1 = "Tubarão";
        final String ANIMAL2 = "Peixe";
        final String ANIMAL3 = "Rato";
        final String ANIMAL4 = "Coelho";

        // Create the root node with the question “Are you a mammal?”
        root = new BTNode<String>(ROOT_QUESTION, null, null);

        // Create and attach the left subtree.
        child = new BTNode<String>(LEFT_QUESTION, null, null);
        child.setLeft(new BTNode<String>(ANIMAL1, null, null));
        child.setRight(new BTNode<String>(ANIMAL2, null, null));
        root.setLeft(child);

        // Create and attach the right subtree.
        child = new BTNode<String>(RIGHT_QUESTION, null, null);
        child.setLeft(new BTNode<String>(ANIMAL3, null, null));
        child.setRight(new BTNode<String>(ANIMAL4, null, null));
        root.setRight(child);
        return root;
    }

    public static void learn(BTNode<String> current) {
        String guessAnimal;   // The animal that was just guessed
        String correctAnimal; // The animal that the user was thinking of
        String newQuestion;   // A question to distinguish the two animals

        // Set Strings for the guessed animal, correct animal and a new question.
        guessAnimal = current.getData();
        System.out.println("Desisto! Qual é o animal? ");
        JFrame frame = new JFrame("Joguinho");

        // prompt the user to enter their name
        String name = JOptionPane.showInputDialog(null, "Desisto! Qual é o animal?");
        if (name == null || name.equals("")) {
            System.exit(0);
        }
        correctAnimal = name;
        String pergunta = JOptionPane.showInputDialog(null, "Faça uma pergunta que diferencie " + correctAnimal + " do " + guessAnimal + ".");
        if (pergunta == null || pergunta.equals("")) {
            System.exit(0);
        }
        newQuestion = pergunta;

        // Put the new question in the current node, and add two new children.
        current.setData(newQuestion);
        if (query("A(o) " + correctAnimal + " " + newQuestion)) {
            current.setLeft(new BTNode<String>(correctAnimal, null, null));
            current.setRight(new BTNode<String>(guessAnimal, null, null));
        } else {
            current.setLeft(new BTNode<String>(guessAnimal, null, null));
            current.setRight(new BTNode<String>(correctAnimal, null, null));
        }
    }
    

    public static boolean query(String prompt) {
        String answer = null;
        Component frame = null;
        int n = JOptionPane.showConfirmDialog(
                frame,
                prompt,
                prompt,
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            answer = "Y";
        }
        if (n == 1) {
            answer = "N";
        }
        return answer.startsWith("Y");
    }

    private static void JOptionPane(String string, int QUESTION_MESSAGE, int YES_NO_OPTION) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
