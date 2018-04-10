package aplicationViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminLoginProperty {

	// textfield login
	private StringProperty loginProperty = new SimpleStringProperty();

	// textfield password
	private StringProperty passwordProperty = new SimpleStringProperty();

	// ON/OFF button
	private BooleanProperty disablePasswordProperty = new SimpleBooleanProperty(false);

	// ON/OFF button
	private BooleanProperty disableButtonProperty = new SimpleBooleanProperty(false);

	public AdminLoginProperty() {
		disablePasswordProperty.bind(loginProperty.isEmpty());
		disableButtonProperty.bind(passwordProperty.isEmpty());
	}

	public StringProperty getLoginProperty() {
		return loginProperty;
	}

	public void setLoginProperty(StringProperty loginProperty) {
		this.loginProperty = loginProperty;
	}

	public StringProperty getPasswordProperty() {
		return passwordProperty;
	}

	public void setPasswordProperty(StringProperty passwordProperty) {
		this.passwordProperty = passwordProperty;
	}

	public BooleanProperty getDisablePasswordProperty() {
		return disablePasswordProperty;
	}

	public void setDisablePasswordProperty(BooleanProperty disablePasswordProperty) {
		this.disablePasswordProperty = disablePasswordProperty;
	}

	public BooleanProperty getDisableButtonProperty() {
		return disableButtonProperty;
	}

	public void setDisableButtonProperty(BooleanProperty disableButtonProperty) {
		this.disableButtonProperty = disableButtonProperty;
	}

}
