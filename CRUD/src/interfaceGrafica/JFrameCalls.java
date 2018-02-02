package interfaceGrafica;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import entidade.*;
import model.*;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class JFrameCalls {

	protected Shell shlIngridAm;
	private Table tableCalls;
	private Text textID;
	private Text text_Call;
	private Text text_Critic;
	private Text text_CreateD;
	private Text text_EndD;
	private CallsModel cm = new CallsModel();

	public static void main(String[] args) {
		try {
			JFrameCalls window = new JFrameCalls();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void preencheTabela(){
		tableCalls.removeAll();
		for(Calls c : cm.listarTudo()){
			TableItem itemTabela = new TableItem(tableCalls, SWT.NONE);
			itemTabela.setText(new String[]{
					String.valueOf(c.getId()),
					c.getCall_label(),
					c.getCall_critic(),
					c.getCall_create_date(),
					c.getCall_expire_date()});
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		preencheTabela();
		shlIngridAm.open();
		shlIngridAm.layout();
		while (!shlIngridAm.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}		
	}

	protected void createContents() {		
		shlIngridAm = new Shell();
		shlIngridAm.setSize(604, 464);
		shlIngridAm.setText("InGRID AM");
		
		tableCalls = new Table(shlIngridAm, SWT.BORDER | SWT.FULL_SELECTION);
		tableCalls.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem[] selecionado = tableCalls.getSelection();
				int id = Integer.parseInt(selecionado[0].getText());
				Calls c = cm.listar(id);
				text_Call.setText(c.getCall_label());
				text_Critic.setText(c.getCall_critic());
				text_CreateD.setText(c.getCall_create_date());				
				text_EndD.setText(c.getCall_expire_date());
				textID.setText(String.valueOf(c.getId()));
			}
		});
		tableCalls.setBounds(23, 10, 542, 168);
		tableCalls.setHeaderVisible(true);
		tableCalls.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(tableCalls, SWT.NONE);
		tblclmnId.setWidth(66);
		tblclmnId.setText("ID");
		
		TableColumn tblclmnChamado = new TableColumn(tableCalls, SWT.NONE);
		tblclmnChamado.setWidth(100);
		tblclmnChamado.setText("Chamado");
		
		TableColumn tblclmnCriticidade = new TableColumn(tableCalls, SWT.NONE);
		tblclmnCriticidade.setWidth(100);
		tblclmnCriticidade.setText("Criticidade");
		
		TableColumn tblclmnDataDeCriao = new TableColumn(tableCalls, SWT.NONE);
		tblclmnDataDeCriao.setWidth(127);
		tblclmnDataDeCriao.setText("CreateDate");
		
		TableColumn tblclmnEnddate = new TableColumn(tableCalls, SWT.NONE);
		tblclmnEnddate.setWidth(140);
		tblclmnEnddate.setText("EndDate");		
		
		Button btnAdicionar = new Button(shlIngridAm, SWT.NONE);
		btnAdicionar.addSelectionListener(new SelectionAdapter() {			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Calls c = new Calls();				
				c.setCall_create_date(text_CreateD.getText());
				c.setCall_expire_date(text_EndD.getText());
				c.setCall_critic(text_Critic.getText());
				c.setCall_label(text_Call.getText());
				if(cm.adicionar(c)){
					JOptionPane.showMessageDialog(null, "Chamado Adicionado com Sucesso!");
					preencheTabela();
				}else{
					JOptionPane.showMessageDialog(null, "Não foi possível inserir chamado. Verificar.");
				}
			}
		});
		btnAdicionar.setBounds(23, 360, 75, 25);
		btnAdicionar.setText("Adicionar");
		
		Button btnRemover = new Button(shlIngridAm, SWT.NONE);
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int resultado = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if(resultado == JOptionPane.YES_OPTION){
					TableItem[] selecionado = tableCalls.getSelection();
					int id = Integer.parseInt(selecionado[0].getText());
					Calls c = cm.listar(id);
					cm.deletar(c);
					preencheTabela();
					JOptionPane.showMessageDialog(null, "Registro deletado!");
				}
			}
		});
		btnRemover.setBounds(224, 360, 75, 25);
		btnRemover.setText("Remover");
		
		Button btnAlterar = new Button(shlIngridAm, SWT.NONE);
		btnAlterar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Calls c = new Calls();
				c.setId(Integer.parseInt(textID.getText()));
				c.setCall_create_date(text_CreateD.getText());
				c.setCall_expire_date(text_EndD.getText());
				c.setCall_critic(text_Critic.getText());
				c.setCall_label(text_Call.getText());
				if(cm.editar(c)){
					JOptionPane.showMessageDialog(null, "Chamado Atualizado com Sucesso!");
					preencheTabela();
				}else{
					JOptionPane.showMessageDialog(null, "Não foi possível atualizar chamado. Verificar.");					
				}				
			}
		});
		btnAlterar.setBounds(129, 360, 75, 25);
		btnAlterar.setText("Alterar");
		
		textID = new Text(shlIngridAm, SWT.BORDER);
		textID.setBounds(108, 204, 181, 21);
		textID.setEditable(false);
		
		text_Call = new Text(shlIngridAm, SWT.BORDER);
		text_Call.setBounds(108, 231, 181, 21);
		
		text_Critic = new Text(shlIngridAm, SWT.BORDER);
		text_Critic.setBounds(108, 258, 181, 21);
		
		text_CreateD = new Text(shlIngridAm, SWT.BORDER);
		text_CreateD.setBounds(108, 285, 181, 21);
		
		text_EndD = new Text(shlIngridAm, SWT.BORDER);
		text_EndD.setBounds(108, 312, 181, 21);
		
		Label lblId = new Label(shlIngridAm, SWT.NONE);
		lblId.setBounds(34, 207, 29, 15);
		lblId.setText("ID");
		
		Label lblC = new Label(shlIngridAm, SWT.NONE);
		lblC.setBounds(34, 234, 55, 15);
		lblC.setText("Chamado");
		
		Label lblCriticidade = new Label(shlIngridAm, SWT.NONE);
		lblCriticidade.setBounds(34, 261, 68, 15);
		lblCriticidade.setText("Criticidade");
		
		Label lblCreatedate = new Label(shlIngridAm, SWT.NONE);
		lblCreatedate.setBounds(34, 288, 63, 15);
		lblCreatedate.setText("CreateDate");
		
		Label lblEnddate = new Label(shlIngridAm, SWT.NONE);
		lblEnddate.setBounds(34, 315, 55, 15);
		lblEnddate.setText("EndDate");
	}
}