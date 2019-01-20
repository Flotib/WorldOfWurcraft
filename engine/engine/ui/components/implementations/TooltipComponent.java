package engine.ui.components.implementations;

import java.util.ArrayList;
import java.util.List;

import engine.game.content.tooltip.TooltipData;
import engine.ui.components.UIComponent;

public abstract class TooltipComponent extends UIComponent {
	
	/* Variables */
	private List<TooltipData> tooltipItems;
	
	/* Constructor */
	public TooltipComponent() {
		this(0, 0);
	}

	/* Constructor */
	public TooltipComponent(double x, double y) {
		this(0, 0, INVALID, INVALID);
	}

	/* Constructor */
	public TooltipComponent(double x, double y, double width, double height) {
		super(x, y, width, height);
		
		this.tooltipItems = new ArrayList<>();
	}
	
	@Override
	public void computeSize() {
		
	}
	
}