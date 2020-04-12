package regalowl.hyperconomy.display;


import regalowl.hyperconomy.HyperConomy;
import regalowl.hyperconomy.account.HyperAccount;
import regalowl.hyperconomy.minecraft.HLocation;
import regalowl.hyperconomy.minecraft.HSign;
import regalowl.hyperconomy.util.LanguageFile;

public class AccountInfoSign extends InfoSign {
    private HyperAccount account;
    private LanguageFile L;
    
    public AccountInfoSign(HyperConomy hc, HLocation loc, String economy, String type, String[] parameters) {
        super(hc,loc,economy,type,parameters);
        L = hc.getLanguageFile();
        account = hc.getDataManager().getAccount(parameters[0]+parameters[1]);
        HSign s = getSign();
        if(s == null || account == null) {
            valid = false;
            return;
        } else {
            valid = true;
        }
        parameters[0] = account.getName();
        lines[0] = hc.getMC().removeColor(s.getLine(0).trim());
		lines[1] = hc.getMC().removeColor(s.getLine(1).trim());
        if (lines[0].length() > 13) {
			lines[1] = "&1" + lines[0].substring(13, lines[0].length()) + lines[1];
			lines[0] = "&1" + lines[0].substring(0, 13);
		} else {
			lines[0] = "&1" + lines[0];
			lines[1] = "&1" + lines[1];
		}
    }
    
    @Override
    public void update() {
        lines[2] = "&f" + "Balance:";
        double balance = account.getBalance();
        if(balance < 0.0)
            lines[3] = "&c" + "" + L.formatMoney(balance);
        else
            lines[3] = "&a" + "" + L.formatMoney(balance);    
        updateHSign();
    }

    public HyperAccount getAccount() {
        return account;
    }
}