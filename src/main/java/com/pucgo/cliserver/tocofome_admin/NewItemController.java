package com.pucgo.cliserver.tocofome_admin;

import com.pucgo.cliserver.tocofome_admin.dao.ItemDAO;
import com.pucgo.cliserver.tocofome_admin.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class NewItemController {


    ItemDAO itemDAO = new ItemDAO();
    Alert alert;
    String message;
    String regexUrl = "^(http|https).+(png|jpe?g)$";
    String regexPrice = "^\\d+\\.\\d{2}$";

    @FXML
    private TextField imageUrlTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Button newItemButton;

    @FXML
    private TextField productTextField;

    @FXML
    void newItemButtonOnClick(ActionEvent event){

        if (validInput()) {
            Item newItem = getItemInfo();
            try {
                newItem = itemDAO.crateItem(newItem);
                message += "\n\nO código do novo produto é: " + newItem.getId();
                alert.setContentText(message);
                clean();
                alert.showAndWait();
            } catch (Exception e) {
                alert.setContentText("Ocorreu um erro!\n" + e.getMessage());
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.show();
        }
    }

    public boolean validInput() {
        if (productTextField.getText().equals("")) {
            message = "O nome do produto não pode ficar em branco.";
            productTextField.requestFocus();
        } else if (descriptionTextField.getText().equals("")) {
            message = "A descrição do produto não pode ficar em branco.";
            productTextField.requestFocus();
        } else if (priceTextField.getText().equals("")) {
            message = "O preço do produto não pode ficar em branco.";
            priceTextField.requestFocus();
        } else if (!priceTextField.getText().matches(regexPrice)) {
            message = "Insira somente números com duas casas decimais separadas por um ponto.\n\nExemplo: 9.89";
            priceTextField.requestFocus();
        } else if (!imageUrlTextField.getText().equals("") && !imageUrlTextField.getText().matches(regexUrl)) {
            message = "Insira somente o URL de imagens de extensão .png, .jpeg, ou .jpg\n\nExemplo: http://exemplo/imagem.png";
            imageUrlTextField.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            message = "Cadastrado(a) com sucesso!";
            return true;
        }
        alert = new Alert(Alert.AlertType.ERROR);
        return false;
    }


    public Item getItemInfo() {
        Item item = new Item();
        item.setProduct(productTextField.getText());
        item.setDescription(descriptionTextField.getText());
        item.setImageUrl(imageUrlTextField.getText());
        item.setPrice(new BigDecimal(priceTextField.getText()));
        return item;
    }

    public void clean(){
        productTextField.setText("");
        descriptionTextField.setText("");
        imageUrlTextField.setText("");
        priceTextField.setText("");
        productTextField.requestFocus();
    }
}
