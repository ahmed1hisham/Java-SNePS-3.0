import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sneps.exceptions.CannotBuildNodeException;
import sneps.exceptions.DuplicateContextNameException;
import sneps.exceptions.DuplicatePropositionException;
import sneps.exceptions.EquivalentNodeException;
import sneps.exceptions.IllegalIdentifierException;
import sneps.exceptions.NodeNotFoundInNetworkException;
import sneps.exceptions.NotAPropositionNodeException;
import sneps.network.Network;
import sneps.network.Node;
import sneps.network.PropositionNode;
import sneps.network.RuleNode;
import sneps.network.VariableNode;
import sneps.network.cables.DownCable;
import sneps.network.cables.DownCableSet;
import sneps.network.classes.CaseFrame;
import sneps.network.classes.RCFP;
import sneps.network.classes.Relation;
import sneps.network.classes.RelationsRestrictedCaseFrame;
import sneps.network.classes.Semantic;
import sneps.network.classes.Wire;
import sneps.network.classes.term.Molecular;
import sneps.network.classes.term.Open;
import sneps.network.classes.term.Term;
import sneps.network.classes.term.Variable;
import sneps.network.classes.setClasses.ContextRuisSet;
import sneps.network.classes.setClasses.FlagNodeSet;
import sneps.network.classes.setClasses.NodeSet;
import sneps.network.classes.setClasses.PropositionSet;
import sneps.snebr.Context;
import sneps.snebr.Controller;
import sneps.snebr.Support;
import sneps.snip.Report;
import sneps.snip.classes.FlagNode;
import sneps.snip.classes.RuisHandler;
import sneps.snip.classes.RuleUseInfo;
import sneps.snip.matching.Binding;
import sneps.snip.matching.LinearSubstitutions;
import sneps.snip.rules.OrEntailment;
import sneps.snip.rules.ThreshEntailment;

public class ThreshTest {
	
	
	private static Context context;
	private static String contextName = "TempContext";
	private static ThreshEntailment thresh;
	private static Node fido, var, dog, barks;
	private static Node prop1, prop2, prop3, prop4;
	private static RuleUseInfo rui;
	private static Report report;
	private static Report report1;
	private static Report report2;
	private static Report report3;
	private static Report report4;
	private static Report report5;
	private static Report report6;
	private static Report report7;
	private static Report report8;
	private static Report report9;
	private static Report report10;
	private static Report report11;
	private static Report report12;
	private static Report report13;
	private static Report report14;
	private static Report report15;
	private static Report report16;
	private static Report report17;
	private static Report report18;
	private static Report report19;
	private static Report report20;
	private static Report report21;
	private static Report report22;
	private static Report report23;
	private static Report report24;
	private static Report report25;
	private static Report report26;
	private static Report report27;
	private static Report report28;
	
	
	@BeforeClass
	public static void BuildNetwork() throws Exception {
		
		
		/**
		 * building context
		 */
		
		try {
			context = Controller.createContext(contextName);
		} catch (DuplicateContextNameException e1) {
			assertNotNull(e1.getMessage(), e1);
		}
		
		
		/**
		 * Create substitutions,
		 * FlagNodeSet,
		 * FlagNode,
		 * PropositionSet,
		 * wires,
		 * relations,
		 * caseFrames
		 */
		
		LinearSubstitutions sub = new LinearSubstitutions();
		FlagNodeSet fns = new FlagNodeSet();
		FlagNode fn;
		PropositionSet support = new PropositionSet();
 		ArrayList<Wire> wires = new ArrayList<Wire>();
 		LinkedList<DownCable> dc = new LinkedList<DownCable>();
		LinkedList<Relation> rels = new LinkedList<Relation>();
		NodeSet nodeSet = new NodeSet();
		Relation memberRel = Network.defineRelation("Member", "NodeSet");
		Relation classRel = Network.defineRelation("Class", "NodeSet");
		Relation doesRel = Network.defineRelation("Does", "NodeSet");
		Relation antsRel = Network.defineRelation("Tant", "Tant");
		Relation consRel = Network.defineRelation("Tconsq", "Tconsq");
		rels.add(memberRel);	rels.add(classRel);
		CaseFrame caseFrameMC = Network.defineCaseFrame("MemberClass", rels);
		rels.clear();		rels.add(classRel);		rels.add(doesRel);
		CaseFrame caseFrameCD = Network.defineCaseFrame("ClassDoes", rels);
		rels.clear();		rels.add(memberRel);		rels.add(doesRel);
 		CaseFrame caseFrameMD = Network.defineCaseFrame("MemberDoes", rels);
		rels.clear();		rels.add(antsRel);		rels.add(consRel);
 		CaseFrame caseFrameAC = Network.defineCaseFrame("AntsCons", rels);
		Wire wire1 = null, wire2 = null, wire3 = null, wire4 = null;
		rels.clear();
		
		
		
		
		/**
		 * Building propositions & base nodes,
		 * adding wires
		 */
		
		
		
		
		
		
		try {
			var = Network.buildVariableNode("X");
			fido = Network.buildBaseNode("Fido", new Semantic("Base"));
			dog = Network.buildBaseNode("Dog", new Semantic("Proposition"));//MolecularNode(wires, caseFrame);
			barks = Network.buildBaseNode("Barks", new Semantic("Proposition"));//MolecularNode(wires, caseFrame);
			wire1 = new Wire(memberRel, fido);
			wire2 = new Wire(classRel, dog);
			wire3 = new Wire(doesRel, barks);
			wire4 = new Wire(memberRel, var);
		} catch (IllegalIdentifierException | NotAPropositionNodeException 
				| NodeNotFoundInNetworkException e1) {
			assertNotNull(e1.getMessage(), e1);
			var = new VariableNode(new Variable("X"));
		}
		
		
		
		
		
		
		
		
		
		
		try {
			wires.clear();	wires.add(wire1);	wires.add(wire2);
			prop1 = Network.buildMolecularNode(wires, caseFrameMC);

			wires.clear();	wires.add(wire2);	wires.add(wire3);
			prop2 = Network.buildMolecularNode(wires, caseFrameCD);

			wires.clear();	wires.add(wire4);	wires.add(wire2);
			prop3 = Network.buildMolecularNode(wires, caseFrameMC);

			wires.clear();	wires.add(wire1);	wires.add(wire3);
			prop4 = Network.buildMolecularNode(wires, caseFrameMD);
		} catch (CannotBuildNodeException | EquivalentNodeException
				| NotAPropositionNodeException | NodeNotFoundInNetworkException e1) {
			assertNotNull(e1.getMessage(), e1);
			LinkedList<DownCable> dcList = new LinkedList<DownCable>();
			NodeSet nodeSet1 = new NodeSet();
			DownCable dc1;	DownCableSet dcs;

			nodeSet1.addNode(fido);
			dc1 = new DownCable(memberRel, nodeSet1);
			dcList.add(dc1);
			nodeSet1.clear();		nodeSet1.addNode(dog);
			dc1 = new DownCable(classRel, nodeSet1);
			dcList.add(dc1);
			dcs = new DownCableSet(dcList, caseFrameMC); 
			prop1 = new Node(new Open("Prop1", dcs));
			dcList.clear();
			//------------------------------------------------------------//
			nodeSet1.clear();		nodeSet1.addNode(dog);
			dc1 = new DownCable(classRel, nodeSet1);
			dcList.add(dc1);
			nodeSet1.clear();		nodeSet1.addNode(barks);
			dc1 = new DownCable(doesRel, nodeSet1);
			dcList.add(dc1);
			dcs = new DownCableSet(dcList, caseFrameCD); 
			prop2 = new Node(new Open("Prop2", dcs));
			dcList.clear();
			//------------------------------------------------------------//
			nodeSet1.clear();		nodeSet1.addNode(var);
			dc1 = new DownCable(memberRel, nodeSet1);
			dcList.add(dc1);
			nodeSet1.clear();		nodeSet1.addNode(dog);
			dc1 = new DownCable(classRel, nodeSet1);
			dcList.add(dc1);
			dcs = new DownCableSet(dcList, caseFrameMC); 
			prop3 = new Node(new Open("Prop3", dcs));
			dcList.clear();
			//------------------------------------------------------------//
			nodeSet1.clear();		nodeSet1.addNode(fido);
			dc1 = new DownCable(memberRel, nodeSet1);
			dcList.add(dc1);
			nodeSet1.clear();		nodeSet1.addNode(barks);
			dc1 = new DownCable(doesRel, nodeSet1);
			dcList.add(dc1);
			dcs = new DownCableSet(dcList, caseFrameMD); 
			prop4 = new Node(new Open("Prop4", dcs));
			dcList.clear();
		}
		
	
		try {
			support.add(prop1.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException
				| NodeNotFoundInNetworkException e) {
			assertNotNull(e.getMessage(), e);
		}
		fn = new FlagNode(prop1, support, 1);
		fns.insert(fn);

		support.clearSet();
		try {
			support.add(prop2.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException
				| NodeNotFoundInNetworkException e) {
			assertNotNull(e.getMessage(), e);
		}
		fn = new FlagNode(prop2, support, 1);
		fns.insert(fn);

		support.clearSet();
		try {
			support.add(prop3.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException
				| NodeNotFoundInNetworkException e) {
			assertNotNull(e.getMessage(), e);
		}
		fn = new FlagNode(prop3, support, 1);
		fns.insert(fn);

		nodeSet.addNode(prop1);
		dc.add(new DownCable(antsRel, nodeSet));

		nodeSet.clear();
		nodeSet.addNode(prop2);
		dc.add(new DownCable(antsRel, nodeSet));

		nodeSet.clear();
		nodeSet.addNode(prop3);
		dc.add(new DownCable(antsRel, nodeSet));

		nodeSet.clear();
		nodeSet.addNode(prop4);
		dc.add(new DownCable(consRel, nodeSet));

		DownCableSet dcs = new DownCableSet(dc, caseFrameAC);
		
		
		/**
		 * Thresh-entailment
		 */
		
		
		thresh= new ThreshEntailment(new Open("Open", dcs));

		try {
			support.add(dog.getId());
			support.add(fido.getId());
			support.add(var.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException
				| NodeNotFoundInNetworkException e) {
			assertNotNull(e.getMessage(), e);
		}

		sub.insert(new Binding((VariableNode) var,fido));
		rui = new RuleUseInfo(sub, 2, 8, fns);
		report = new Report(sub, support, false, contextName);
		
		report = new Report(sub, support, true, contextName);
		report1 = new Report(sub, support, true, contextName);
		report2 = new Report(sub, support, false, contextName);
		report3 = new Report(sub, support, false, contextName);
		report4 = new Report(sub, support, false, contextName);
		report5 = new Report(sub, support, false, contextName);
		report6 = new Report(sub, support, false, contextName);
		report7 = new Report(sub, support, false, contextName);
		report8 = new Report(sub, support, false, contextName);
		report9 = new Report(sub, support, false, contextName);
	
		
		report10 = new Report(sub, support, true, contextName);
		report11 = new Report(sub, support, true, contextName);
		report12 = new Report(sub, support, true, contextName);
		report13 = new Report(sub, support, true, contextName);
		report14 = new Report(sub, support, false, contextName);
		report15 = new Report(sub, support, false, contextName);
		report16 = new Report(sub, support, false, contextName);
		report17 = new Report(sub, support, false, contextName);
		report18 = new Report(sub, support, false, contextName);
		report19 = new Report(sub, support, false, contextName);
		
		
		report20 = new Report(sub, support, true, contextName);
		report21 = new Report(sub, support, true, contextName);
		report22 = new Report(sub, support, true, contextName);
		report23 = new Report(sub, support, true, contextName);
		report24 = new Report(sub, support, false, contextName);
		report25 = new Report(sub, support, false, contextName);
		report26 = new Report(sub, support, false, contextName);
		report27 = new Report(sub, support, false, contextName);
		report28 = new Report(sub, support, false, contextName);
	
		thresh.setThreshArgs(10);
		thresh.setThreshMax(6);
		thresh.setThreshMin(3);
	}
	
	
	@Test
	public void test() {
		thresh.setThreshArgs(10);
		thresh.setThreshMax(6);
		thresh.setThreshMin(3);
		thresh.applyRuleHandler(report, fido);
		thresh.applyRuleHandler(report1, fido);
		thresh.applyRuleHandler(report2, fido);
		thresh.applyRuleHandler(report3, fido);
		thresh.applyRuleHandler(report4, fido);
		thresh.applyRuleHandler(report5, fido);
		thresh.applyRuleHandler(report6, fido);
		thresh.applyRuleHandler(report7, fido);
		thresh.applyRuleHandler(report8, fido);
		thresh.applyRuleHandler(report9, fido);
		//assertEquals(2, thresh.getPos());
		//assertEquals(8, thresh.getNeg());
		assertEquals(3,thresh.getThreshMin());
		assertEquals(6,thresh.getThreshMax());
		assertEquals(10,thresh.getThreshArgs());
		assertEquals(true, thresh.getSign());
		thresh.clrAll();
	}
	
	@Test
	public void testTwo() {
		thresh.setThreshArgs(10);
		thresh.setThreshMax(6);
		thresh.setThreshMin(3);
		thresh.applyRuleHandler(report10, fido);
		thresh.applyRuleHandler(report11, fido);
		thresh.applyRuleHandler(report12, fido);
		thresh.applyRuleHandler(report13, fido);
		thresh.applyRuleHandler(report14, fido);
		thresh.applyRuleHandler(report15, fido);
		thresh.applyRuleHandler(report16, fido);
		thresh.applyRuleHandler(report17, fido);
		thresh.applyRuleHandler(report18, fido);
		thresh.applyRuleHandler(report19, fido);
		//assertEquals(4, thresh.getPos());
		//assertEquals(6, thresh.getNeg());
		assertEquals(3,thresh.getThreshMin());
		assertEquals(6,thresh.getThreshMax());
		assertEquals(10,thresh.getThreshArgs());
		assertEquals(false, thresh.getSign());
		thresh.clrAll();
	}
	
	@Test
	public void testThree() {
		thresh.setThreshArgs(10);
		thresh.setThreshMax(6);
		thresh.setThreshMin(3);
		thresh.applyRuleHandler(report20, fido);
		thresh.applyRuleHandler(report21, fido);
		thresh.applyRuleHandler(report22, fido);
		thresh.applyRuleHandler(report23, fido);
		thresh.applyRuleHandler(report24, fido);
		thresh.applyRuleHandler(report25, fido);
		thresh.applyRuleHandler(report26, fido);
		thresh.applyRuleHandler(report27, fido);
		thresh.applyRuleHandler(report28, fido);
		//assertEquals(4, thresh.getPos());
		//assertEquals(5, thresh.getNeg());
		assertEquals(3,thresh.getThreshMin());
		assertEquals(6,thresh.getThreshMax());
		assertEquals(10,thresh.getThreshArgs());
		assertEquals(false, thresh.getSign());
		thresh.clrAll();
	}
}