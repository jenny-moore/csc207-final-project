package view;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.choose_genre.ChooseController;
public class ChooseView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "choose";

    private final ChooseViewModel chooseViewModel;
    private final ChooseController chooseController;

    private final JButton choose;
    private final JComboBox<String> genres;

    public SignupView(ChooseController controller, ChooseViewModel chooseViewModel) {
        this.chooseController = controller;
        this.chooseViewModel = chooseViewModel;

        chooseViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(ChooseViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        genres = newJComboBox(ChooseViewModel.GENRES);

        LabelTextPanel genresInfo = new LabelTextPanel(
                new JLabel(ChooseViewModel.GENRE_LABEL), genres);

        JPanel buttons = new JPanel();
        choose = new JButton(ChooseViewModel.CHOOSE_BUTTON_LABEL);
        buttons.add(choose);


        choose.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(choose)) {
                            ChooseState currentState = chooseViewModel.getState();

                            chooseController.execute(
                                    currentState.getGenre()
                            );
                        }
                    }
                }
        );

        // TODO Add the body to the actionPerformed method of the action listener below
        //      for the "clear" button. You'll need to write the controller before
        //      you can complete this.

        clear.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(clear)){
                            clearController.execute();
                        }}
                }
        );



        cancel.addActionListener(this);

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }}
        else {
            ClearState clearState = (ClearState) evt.getNewValue();
            String message = new String("Deleted users: \n");
            for (String user: clearState.getDeletedUsers()){
                message += user + "\n";
            }
            JOptionPane.showMessageDialog(this, message);
        }
    }

}
